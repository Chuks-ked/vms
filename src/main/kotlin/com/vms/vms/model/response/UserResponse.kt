package com.vms.vms.model.response

import java.time.LocalDateTime

data class UserResponse(
    val id: Long?,
    val username: String,
    val email: String,
    val fullName: String,
    val department: String?,
    val roleName: String,
    val createdAt: LocalDateTime
)