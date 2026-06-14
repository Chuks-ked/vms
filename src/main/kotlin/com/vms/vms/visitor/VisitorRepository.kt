package com.vms.vms.visitor

import org.springframework.data.jpa.repository.JpaRepository

interface VisitorRepository: JpaRepository<Visitor, Long> {
    fun findByEmail (email: String): Visitor?
    fun existsByEmail(email: String): Boolean
}