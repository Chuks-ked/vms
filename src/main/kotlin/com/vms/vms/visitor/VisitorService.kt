package com.vms.vms.visitor

import com.vms.vms.exception.DuplicateResourceException
import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.visitor.dto.CreateVisitorRequest
import com.vms.vms.visitor.dto.UpdateVisitorRequest
import com.vms.vms.visitor.dto.VisitorResponse
import org.springframework.stereotype.Service

@Service
class VisitorService(
    private val visitorRepository: VisitorRepository
) {

    fun createVisitor(
        request: CreateVisitorRequest
    ): VisitorResponse {
        if(visitorRepository.existsByEmail(request.email)){
            throw DuplicateResourceException("Visitor already exists")
        }

        val visitor = visitorRepository.save(
            Visitor(
                fullName = request.fullName,
                email = request.email,
                phoneNumber = request.phoneNumber,
                company = request.company,
                identificationType = request.identificationType,
                identificationNumber = request.identificationNumber,
            )
        )

        return visitor.toResponse()
    }

    fun getAllVisitors(): List<VisitorResponse> {
        return visitorRepository.findAll().map { it.toResponse() }
    }

    fun getVisitor(id: Long): VisitorResponse {
        val visitor = visitorRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visitor not found") }

        return visitor.toResponse()
    }

    fun updateVisitor(
        id: Long,
        request: UpdateVisitorRequest
    ): VisitorResponse {
        val visitor = visitorRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visitor not found") }

        visitor.fullName = request.fullName
        visitor.email = request.email
        visitor.phoneNumber = request.phoneNumber
        visitor.company = request.company
        visitor.identificationType = request.identificationType
        visitor.identificationNumber = request.identificationNumber

        val updatedVisitor = visitorRepository.save(visitor) // verify to ensure it outputs the updated data

        return updatedVisitor.toResponse()
    }

    fun deleteVisitor(id: Long) {
        val visitor = visitorRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Visitor not found") }

        visitorRepository.delete(visitor)
    }


    private fun Visitor.toResponse() =
        VisitorResponse(
            id = id!!,
            fullName = fullName,
            email = email,
            phoneNumber = phoneNumber,
            company = company,
            identificationType = identificationType,
            identificationNumber = identificationNumber,
            createdAt = createdAt,
        )
}