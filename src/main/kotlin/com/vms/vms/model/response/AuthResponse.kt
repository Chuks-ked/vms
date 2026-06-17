package com.vms.vms.model.response

data class AuthResponse(
    val token: String
)

data class MeResponse(
    val email: String,
    val role: String
)