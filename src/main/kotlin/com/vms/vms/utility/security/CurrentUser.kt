package com.vms.vms.utility.security

import com.vms.vms.model.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUser {

    fun getUser(): User {

        val principal = SecurityContextHolder.getContext().authentication!!.principal as CustomUserDetails

        return principal.getUser()
    }
}