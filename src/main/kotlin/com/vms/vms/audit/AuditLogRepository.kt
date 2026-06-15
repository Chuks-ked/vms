package com.vms.vms.audit

import org.springframework.data.jpa.repository.JpaRepository

interface AuditLogRepository: JpaRepository<AuditLog, Long> {
    fun findByEntityTypeAndEntityId(
        entityType: String,
        entityId: Long)
    : List<AuditLog>
}
