package com.vms.vms.user

import com.vms.vms.exception.DuplicateResourceException
import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.role.RoleRepository
import com.vms.vms.user.dto.CreateUserRequest
import com.vms.vms.user.dto.UpdateUserRequest
import com.vms.vms.user.dto.UserResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(
        request: CreateUserRequest,
    ): UserResponse {
        if (userRepository.existsByUsername(request.username)) {
            throw DuplicateResourceException("Username already exists")
        }

        if (userRepository.existsByEmail(request.email)) {
            throw DuplicateResourceException("Email already exists")
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
//        val hashedPassword =
//            passwordEncoder.encode(
//                request.password
//            )

        val user = userRepository.save(
            User(
                username = request.username,
                email = request.email,
                passwordHash = hashedPassword,
                fullName = request.fullName,
                department = request.department,
                role = role,
            )
        )

        return UserResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            fullName = user.fullName,
            department = user.department,
            roleName = user.role.roleName,
            createdAt = user.createdAt,
        )
    }

    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map {
            UserResponse(
                id = it.id,
                username = it.username,
                email = it.email,
                fullName = it.fullName,
                department = it.department,
                roleName = it.role.roleName,
                createdAt = it.createdAt,
            )
        }
    }

    fun getUser(id: Long): UserResponse {
        val user = userRepository.findById(id).orElseThrow{
            ResourceNotFoundException("User not found")
        }

        return UserResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            fullName = user.fullName,
            department = user.department,
            roleName = user.role.roleName,
            createdAt = user.createdAt,
        )
    }

    fun updateUser(
        id: Long,
        request: UpdateUserRequest,
    ):  UserResponse {
        val user = userRepository.findById(id).orElseThrow {
            ResourceNotFoundException("User not found")
        }

        val newRole = roleRepository.findById(request.roleId).orElseThrow {
            ResourceNotFoundException("Role not found")
        }

        user.fullName = request.fullName
        user.department = request.department
        user.role = newRole
//      user.role = roleRepository.getReferenceById(request.roleId) //to avoid hitting the db always

        val updated = userRepository.save(user)

        return UserResponse(
            id = updated.id,
            username = updated.username,
            email = updated.email,
            fullName = updated.fullName,
            department = updated.department,
            roleName = user.role.roleName,
            createdAt = updated.createdAt,
        )
    }

    fun deleteUser(id: Long) {
        val user = userRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("User not found")
            }
        userRepository.delete(user)
    }
}