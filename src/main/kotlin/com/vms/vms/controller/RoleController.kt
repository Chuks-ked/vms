package com.vms.vms.controller

import com.vms.vms.service.RoleService
import com.vms.vms.model.request.CreateRoleRequest
import com.vms.vms.model.response.RoleResponse
import com.vms.vms.model.request.UpdateRoleRequest
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
@RequestMapping("/api/v1/roles")
class RoleController(
    private val roleService: RoleService
) {

    @PostMapping
    fun createRole(
        @Valid
        @RequestBody request: CreateRoleRequest
    ): RoleResponse {
        return roleService.createRole(request)
    }

    @GetMapping
    fun getRoles() = roleService.getAllRoles()

    @GetMapping("/{id}")
    fun getRole(
        @PathVariable id: Long
    ) = roleService.getRole(id)

    @PutMapping("/{id}")
    fun updateRole(
        @PathVariable id: Long,
        @Valid
        @RequestBody request: UpdateRoleRequest
    ) = roleService.updateRole(id, request)

    @DeleteMapping("/{id}")
    fun deleteRole(
        @PathVariable id: Long
    ) = roleService.deleteRole(id)



}