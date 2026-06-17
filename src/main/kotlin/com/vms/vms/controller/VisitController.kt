package com.vms.vms.controller

import com.vms.vms.model.request.CreateVisitRequest
import com.vms.vms.model.response.VisitResponse
import com.vms.vms.service.AuditService
import com.vms.vms.service.VisitService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/visits")
class VisitController(
    private val visitService: VisitService,
    private val auditService: AuditService
) {

    @PostMapping
    fun createVisit(
        @Valid
        @RequestBody request: CreateVisitRequest
    ): VisitResponse {
        return visitService.createVisit(request)
    }

    @GetMapping
    fun getAllVisits() = visitService.getAllVisits()

    @GetMapping("/{id}")
    fun getVisit(
        @PathVariable id: Long
    ) = visitService.getVisit(id)

    @PostMapping("/{id}/approve")
    fun approveVisit(
        @PathVariable id: Long,
    ) = visitService.approveVisit(id)

    @PostMapping("/{id}/reject")
    fun rejectVisit(
        @PathVariable id: Long,
    ) = visitService.rejectVisit(id)

    @PostMapping("/{id}/cancel")
    fun cancelVisit(
        @PathVariable id: Long,
    ) = visitService.cancelVisit(id)

    @PostMapping("/{id}/check-in")
    fun checkInVisit(
        @PathVariable id: Long,
    ) = visitService.checkInVisit(id)

    @PostMapping("/{id}/check-out")
    fun checkOutVisit(
        @PathVariable id: Long,
    ) = visitService.checkOutVisit(id)

    @GetMapping("/{id}/history")
    fun getVisitHistory(
        @PathVariable id: Long,
    ) = auditService.getVisitHistory(id)
}