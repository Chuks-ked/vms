package com.vms.vms.common

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)
