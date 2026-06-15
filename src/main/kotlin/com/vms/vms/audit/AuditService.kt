package com.vms.vms.audit

import com.vms.vms.audit.dto.AuditResponse
import com.vms.vms.user.User
import org.springframework.stereotype.Service

@Service
class AuditService(
    private val auditLogRepository: AuditLogRepository
) {

    fun getVisitHistory(visitId: Long): List<AuditResponse> {
        return auditLogRepository.findByEntityTypeAndEntityId(
            "VISIT",
            visitId
        ).map {
            AuditResponse(
                user = it.user.fullName,
                action = it.action,
                createdAt = it.createdAt,
            )
        }
    }

    fun log(
        user: User,
        action: String,
        entityType: String,
        entityId: Long
    ) {
        auditLogRepository.save(
            AuditLog(
                user = user,
                action = action,
                entityType = entityType,
                entityId = entityId,
            )
        )
    }



}