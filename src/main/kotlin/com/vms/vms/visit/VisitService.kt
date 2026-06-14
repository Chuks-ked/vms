package com.vms.vms.visit

import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.user.UserRepository
import com.vms.vms.visit.dto.CreateVisitRequest
import com.vms.vms.visit.dto.UpdateVisitStatusRequest
import com.vms.vms.visit.dto.VisitResponse
import com.vms.vms.visitor.VisitorRepository
import org.springframework.stereotype.Service

@Service
class VisitService(
    private val visitRepository: VisitRepository,
    private val visitorRepository: VisitorRepository,
    private val userRepository: UserRepository,
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

    fun updateVisit(
        id: Long,
        request: UpdateVisitStatusRequest
    ): VisitResponse {
        val visit = visitRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visit not found") }

        visit.status = request.status

        val updatedVisit = visitRepository.save(visit)

        return updatedVisit.toResponse()
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
}