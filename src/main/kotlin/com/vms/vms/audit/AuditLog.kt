package com.vms.vms.audit

import com.vms.vms.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "audit_logs")
class AuditLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false)
    val action: String,

    @Column(nullable = false)
    val entityType: String,

    @Column(nullable = false)
    val entityId: Long,

    val createdAt: LocalDateTime =
        LocalDateTime.now()
)