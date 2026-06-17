package com.vms.vms.utility.security

import com.vms.vms.model.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val user: User
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(
            SimpleGrantedAuthority("ROLE_${user.role.roleName}")
        )

    override fun getPassword(): String = user.passwordHash
    override fun getUsername(): String = user.email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

    fun getUser(): User = user
}