package com.vms.vms.auth

import com.vms.vms.auth.dto.AuthResponse
import com.vms.vms.auth.dto.LoginRequest
import com.vms.vms.auth.dto.MeResponse
import com.vms.vms.auth.dto.RegisterRequest
import com.vms.vms.exception.DuplicateResourceException
import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.role.RoleRepository
import com.vms.vms.security.JwtService
import com.vms.vms.user.User
import com.vms.vms.user.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
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

    fun me(): MeResponse {

        val email = SecurityContextHolder.getContext().authentication!!.principal as String

//        val email =
//            SecurityContextHolder
//                .getContext()
//                .authentication
//                .principal as String

        val user = userRepository.findByEmail(email)
                ?: throw ResourceNotFoundException(
                    "User not found"
                )

        return MeResponse(
            email = user.email,
            role = user.role.roleName
        )
    }
}