package com.vms.vms.user.dto

import jakarta.validation.constraints.NotBlank

data class UpdateUserRequest(

    @field:NotBlank(message = "Name is required")
    val fullName: String,

    @field:NotBlank(message = "Department is required")
    val department: String,

    val roleId: Long
)