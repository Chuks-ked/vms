package com.vms.vms.repository

import com.vms.vms.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {  //with jpaRep, spring gives us method to work with
    fun existsByRoleName(roleName: String): Boolean
}