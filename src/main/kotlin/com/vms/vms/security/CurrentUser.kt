package com.vms.vms.security

import com.vms.vms.exception.ResourceNotFoundException
import com.vms.vms.user.User
import com.vms.vms.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUser(
    private val userRepository: UserRepository
) {

    fun getUser(): User {

        val email = SecurityContextHolder.getContext().authentication!!.principal as String

        return userRepository.findByEmail(email)
            ?: throw ResourceNotFoundException("Authenticated user not found")
    }
}