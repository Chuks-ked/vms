package com.vms.vms.controller

import com.vms.vms.model.request.CreateVisitorRequest
import com.vms.vms.model.request.UpdateVisitorRequest
import com.vms.vms.model.response.VisitorResponse
import com.vms.vms.service.VisitorService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/visitors")
class VisitorController(
    private val visitorService: VisitorService
) {

    @PostMapping
    fun createVisitor(
        @Valid
        @RequestBody request: CreateVisitorRequest
    ): VisitorResponse {
        return visitorService.createVisitor(request)
    }

    @GetMapping
    fun getAllVisitors() = visitorService.getAllVisitors()

    @GetMapping("/{id}")
    fun getVisitor(
        @PathVariable id: Long
    ) = visitorService.getVisitor(id)

    @PutMapping("/{id}")
    fun updateVisitor(
        @PathVariable id: Long,
        @Valid
        @RequestBody request: UpdateVisitorRequest
    ) = visitorService.updateVisitor(id, request)

    @DeleteMapping("/{id}")
    fun deleteVisitor(
        @PathVariable id: Long
    ) = visitorService.deleteVisitor(id)
}