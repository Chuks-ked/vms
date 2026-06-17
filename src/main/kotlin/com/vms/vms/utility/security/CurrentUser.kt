package com.vms.vms.utility.security

import com.vms.vms.utility.exception.ResourceNotFoundException
import com.vms.vms.model.entity.User
import com.vms.vms.repository.UserRepository
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