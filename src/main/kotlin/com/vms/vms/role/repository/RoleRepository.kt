package com.vms.vms.role.repository

import com.vms.vms.role.enity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {  //with jpaRep, spring gives us method to work with
    fun existsByRoleName(roleName: String): Boolean
}