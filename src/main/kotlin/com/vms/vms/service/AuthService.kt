package com.vms.vms.service

import com.vms.vms.utility.exception.DuplicateResourceException
import com.vms.vms.utility.exception.ResourceNotFoundException
import com.vms.vms.model.request.LoginRequest
import com.vms.vms.model.request.RegisterRequest
import com.vms.vms.model.response.AuthResponse
import com.vms.vms.repository.RoleRepository
import com.vms.vms.utility.security.JwtService
import com.vms.vms.model.entity.User
import com.vms.vms.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(

    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun register(
        request: RegisterRequest,
    ): AuthResponse {
        if (userRepository.existsByUsername(request.username)) {
            throw DuplicateResourceException(
                "Username already exists"
            )
        }

        if (userRepository.existsByEmail(request.email)) {
            throw DuplicateResourceException(
                "Email already exists"
            )
        }

        val role = roleRepository.findById(request.roleId)
            .orElseThrow {
                ResourceNotFoundException(
                    "Role not found"
                )
            }

        val hashedPassword =
            passwordEncoder.encode(
                request.password
            ) ?: throw IllegalArgumentException("Password encoding failed")

        val user = User(
            username = request.username,
            email = request.email,
            passwordHash = hashedPassword,
            fullName = request.fullName,
            department = request.department,
            role = role
        )

        val savedUser = userRepository.save(user)

        val token = jwtService.generateToken(
            savedUser.email
        )

        return AuthResponse(token = token)
    }

    fun login(
        request: LoginRequest
    ): AuthResponse {
        val user = userRepository.findByEmail(request.email)

        if(!passwordEncoder.matches(
                request.password,
                user?.passwordHash
            )) {
            throw BadCredentialsException(
                "Invalid credentials"
            )
        }

        val token =
            jwtService.generateToken(
                user?.email
            )

        return AuthResponse(token)
    }
}