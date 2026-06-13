package com.vms.vms.auth

import com.vms.vms.auth.dto.LoginRequest
import com.vms.vms.auth.dto.RegisterRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(
        @Valid
        @RequestBody request: RegisterRequest
    ) = authService.register(request)

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody request: LoginRequest
    ) = authService.login(request)
}