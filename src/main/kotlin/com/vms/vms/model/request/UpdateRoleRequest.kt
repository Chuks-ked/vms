package com.vms.vms.model.request

import jakarta.validation.constraints.NotBlank

data class UpdateRoleRequest (
    @field:NotBlank(message = "Role name is required")
    val roleName: String,

    @field:NotBlank(message = "Description is required")
    val description: String
)