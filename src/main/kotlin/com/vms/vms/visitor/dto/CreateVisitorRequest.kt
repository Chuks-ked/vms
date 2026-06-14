package com.vms.vms.visitor.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateVisitorRequest (
    @field:NotBlank
    val fullName: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val phoneNumber: String,

    val company: String?,

    val identificationType: String?,

    val identificationNumber: String?
)