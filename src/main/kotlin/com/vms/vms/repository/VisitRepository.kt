package com.vms.vms.repository

import com.vms.vms.model.entity.Visit
import com.vms.vms.model.response.DailyVisitReport
import com.vms.vms.model.response.VisitStatusReport
import com.vms.vms.utility.VisitStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface VisitRepository: JpaRepository<Visit, Long> {
    fun countByStatus(status: VisitStatus): Long
    fun countByVisitDate(visitDate: LocalDate): Long


    @Query("""
        SELECT new com.vms.vms.model.response.DailyVisitReport(v.visitDate, COUNT(v))
        FROM Visit v
        GROUP BY v.visitDate
        ORDER BY v.visitDate DESC
    """)
    fun visitsPerDay(): List<DailyVisitReport>

    @Query("""
        SELECT new com.vms.vms.model.response.VisitStatusReport(v.status, COUNT(v))
        FROM Visit v
        GROUP BY v.status
    """)
    fun visitsByStatus(): List<VisitStatusReport>
}