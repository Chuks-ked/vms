package com.vms.vms.controller

import com.vms.vms.service.AuthService
import com.vms.vms.model.request.LoginRequest
import com.vms.vms.model.request.RegisterRequest
import com.vms.vms.utility.security.CurrentUser
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val currentUser: CurrentUser
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

    @GetMapping("/user")
    fun user() = currentUser.getUser()
}