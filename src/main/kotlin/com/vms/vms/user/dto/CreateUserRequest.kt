package com.vms.vms.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest(

    @field:NotBlank
    val username: String,

    @field:Email
    val email: String,

    @field:NotBlank
    @field:Size(min = 6, message = "Password must be at least 6 characters long")
    val password: String,

    @field:NotBlank
    val fullName: String,

    @field:NotBlank
    val department: String,

    val roleId: Long
)