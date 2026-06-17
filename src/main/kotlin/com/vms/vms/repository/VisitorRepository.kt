package com.vms.vms.repository

import com.vms.vms.model.entity.Visitor
import org.springframework.data.jpa.repository.JpaRepository

interface VisitorRepository: JpaRepository<Visitor, Long> {
    fun findByEmail (email: String): Visitor?
    fun existsByEmail(email: String): Boolean
}