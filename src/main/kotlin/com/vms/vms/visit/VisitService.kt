package com.vms.vms.visit

import com.vms.vms.audit.AuditService
import com.vms.vms.exception.AccessDeniedException
import com.vms.vms.exception.InvalidVisitStatusException
import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.security.CurrentUser
import com.vms.vms.user.UserRepository
import com.vms.vms.visit.dto.CreateVisitRequest
import com.vms.vms.visit.dto.VisitResponse
import com.vms.vms.visitor.VisitorRepository
import org.springframework.stereotype.Service


@Service
class VisitService(
    private val visitRepository: VisitRepository,
    private val visitorRepository: VisitorRepository,
    private val userRepository: UserRepository,
    private val currentUser: CurrentUser,
    private val auditService: AuditService
) {

    fun createVisit(
        request: CreateVisitRequest
    ): VisitResponse {

        val visitor = visitorRepository.findById(request.visitorId)
            .orElseThrow { ResourceNotFoundException("Visitor not found") }

        val host = userRepository.findById(request.hostId)
            .orElseThrow { ResourceNotFoundException("Host not found") }

        val visit = visitRepository.save(
            Visit(
                visitor = visitor,
                host = host,
                purpose = request.purpose,
                visitDate = request.visitDate
            )
        )

        return visit.toResponse()
    }

    fun getAllVisits(): List<VisitResponse> {
        return visitRepository.findAll().map { it.toResponse() }
    }

    fun getVisit(id: Long): VisitResponse {
        val visit = visitRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visit not found") }

        return visit.toResponse()
    }

    fun approveVisit(id: Long): VisitResponse {
        requireRole("ADMIN", "HOST")

        val visit = getVisitEntity(id)
        if(visit.status != VisitStatus.PENDING){
            throw InvalidVisitStatusException("Only pending visit can be approved")
        }

        visit.status = VisitStatus.APPROVED

        auditService.log(
            user = currentUser.getUser(),
            action = "APPROVED_VISIT",
            entityType = "VISIT",
            entityId = visit.id!!
        )

        return visitRepository
            .save(visit).toResponse()
    }

    fun rejectVisit(id: Long): VisitResponse {
        requireRole("ADMIN", "HOST")

        val visit = getVisitEntity(id)
        if(visit.status != VisitStatus.PENDING){
            throw InvalidVisitStatusException("Only pending visit can be rejected")
        }

        visit.status = VisitStatus.REJECTED

        auditService.log(
            user = currentUser.getUser(),
            action = "REJECTED_VISIT",
            entityType = "VISIT",
            entityId = visit.id!!
        )

        return visitRepository
            .save(visit).toResponse()
    }

    fun cancelVisit(id: Long): VisitResponse {
        requireRole("ADMIN", "RECEPTIONIST")

        val visit = getVisitEntity(id)
        if(
            visit.status != VisitStatus.PENDING &&
            visit.status != VisitStatus.APPROVED
        ){
            throw InvalidVisitStatusException("Only pending or approved visit can be cancelled")
        }

        visit.status = VisitStatus.CANCELLED

        auditService.log(
            user = currentUser.getUser(),
            action = "CANCELLED_VISIT",
            entityType = "VISIT",
            entityId = visit.id!!
        )

        return visitRepository
            .save(visit).toResponse()
    }

    fun checkInVisit(id: Long): VisitResponse {
        requireRole("ADMIN")

        val visit = getVisitEntity(id)
        if (visit.status != VisitStatus.APPROVED){
            throw InvalidVisitStatusException("Only approved visit can be checked-in")
        }

        visit.status = VisitStatus.CHECKED_IN

        auditService.log(
            user = currentUser.getUser(),
            action = "CHECKED_IN_VISIT",
            entityType = "VISIT",
            entityId = visit.id!!
        )

        return visitRepository
            .save(visit).toResponse()
    }

    fun checkOutVisit(id: Long): VisitResponse {
        requireRole("ADMIN", "RECEPTIONIST")

        val visit = getVisitEntity(id)
        if (visit.status != VisitStatus.CHECKED_IN){
            throw InvalidVisitStatusException("Only checked-in visit can be checked-out")
        }

        visit.status = VisitStatus.CHECKED_OUT

        auditService.log(
            user = currentUser.getUser(),
            action = "CHECKED_OUT_VISIT",
            entityType = "VISIT",
            entityId = visit.id!!
        )

        return visitRepository
            .save(visit).toResponse()
    }

    private fun Visit.toResponse() =
        VisitResponse(
            id = id!!,
            visitorName = visitor.fullName,
            hostName = host.fullName,
            purpose = purpose,
            visitDate = visitDate,
            status = status
        )

    private fun getVisitEntity(id: Long): Visit {
        return visitRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visit not found") }
    }

    private fun requireRole(
        vararg roles: String
    ) {
        val user = currentUser.getUser()

        if(user.role.roleName !in roles){
            throw AccessDeniedException("Access Denied")
        }
    }
}