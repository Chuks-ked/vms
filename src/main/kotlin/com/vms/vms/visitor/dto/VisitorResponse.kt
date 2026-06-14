package com.vms.vms.visitor.dto

import java.time.LocalDateTime

data class VisitorResponse(
    val id: Long,

    val fullName: String,

    val email: String,

    val phoneNumber: String,

    val company: String?,

    val identificationType: String?,

    val identificationNumber: String?,

    val createdAt: LocalDateTime
)