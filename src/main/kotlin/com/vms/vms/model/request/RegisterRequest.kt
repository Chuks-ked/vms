package com.vms.vms.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(

    @field:NotBlank
    val username: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val fullName: String,

    @field:NotBlank
    val department: String,

    val roleId: Long
)