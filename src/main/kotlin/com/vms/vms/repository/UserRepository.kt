package com.vms.vms.repository

import com.vms.vms.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean

    @Query("""
        SELECT u
        FROM User u
        JOIN FETCH u.role
        WHERE u.email = :email
    """)
    fun findByEmailWithRole(email: String): User?

}