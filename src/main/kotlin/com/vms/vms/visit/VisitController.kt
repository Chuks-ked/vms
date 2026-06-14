package com.vms.vms.visit

import com.vms.vms.visit.dto.CreateVisitRequest
import com.vms.vms.visit.dto.UpdateVisitStatusRequest
import com.vms.vms.visit.dto.VisitResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/visits")
class VisitController(
    private val visitService: VisitService
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

    @PutMapping("/{id}")
    fun updateVisit(
        @PathVariable id: Long,
        @RequestBody request: UpdateVisitStatusRequest
    ) = visitService.updateVisit(id, request)
}