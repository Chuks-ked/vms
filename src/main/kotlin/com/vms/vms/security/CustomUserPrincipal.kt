package com.vms.vms.security

import com.vms.vms.user.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserPrincipal(
    private val user: User
) : UserDetails {
    override fun getUsername() =
        user.email

    override fun getPassword() =
        user.passwordHash

    override fun getAuthorities() =
        listOf(
            SimpleGrantedAuthority(
                "ROLE_${user.role.roleName}"
            )
        )
}