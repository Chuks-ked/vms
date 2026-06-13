package com.vms.vms.user.dto

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