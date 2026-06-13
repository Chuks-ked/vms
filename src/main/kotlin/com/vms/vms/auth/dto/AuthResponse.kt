package com.vms.vms.auth.dto

data class AuthResponse(
    val token: String
)

data class MeResponse(
    val email: String,
    val role: String
)