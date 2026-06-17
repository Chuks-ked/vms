package com.vms.vms.model.request

import jakarta.validation.constraints.NotBlank

data class CreateRoleRequest (
    @field:NotBlank(message = "Role name is required")
    val roleName: String,

    @field:NotBlank(message = "Role description is required")
    val description: String
)