package com.vms.vms.audit.dto

import java.time.LocalDateTime

data class AuditResponse(
    val user: String,
    val action: String,
    val createdAt: LocalDateTime,
)
