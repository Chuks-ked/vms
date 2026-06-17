package com.vms.vms.model.response

import java.time.LocalDateTime

data class AuditResponse(
    val user: String,
    val action: String,
    val createdAt: LocalDateTime,
)