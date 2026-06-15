package com.vms.vms.dashboard

import com.vms.vms.dashboard.dto.DailyVisitReport
import com.vms.vms.dashboard.dto.DashboardResponse
import com.vms.vms.dashboard.dto.VisitStatusReport
import com.vms.vms.visit.VisitRepository
import com.vms.vms.visit.VisitStatus
import com.vms.vms.visitor.VisitorRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DashboardService(
    private val visitRepository: VisitRepository,
    private val visitorRepository: VisitorRepository
) {

    fun dashboard(): DashboardResponse {
        return DashboardResponse(
            totalVisitors = visitorRepository.count(),
            totalVisits = visitRepository.count(),
            pendingVisits = visitRepository.countByStatus(VisitStatus.PENDING),
            approvedVisits = visitRepository.countByStatus(VisitStatus.APPROVED),
            rejectedVisits = visitRepository.countByStatus(VisitStatus.REJECTED),
            checkedInVisits = visitRepository.countByStatus(VisitStatus.CHECKED_IN),
            checkOutVisits = visitRepository.countByStatus(VisitStatus.CHECKED_OUT),
            canceledVisits = visitRepository.countByStatus(VisitStatus.CANCELLED),
            todayVisits = visitRepository.countByVisitDate(LocalDate.now())
        )
    }

    fun visitsPerDay(): List<DailyVisitReport> = visitRepository.visitsPerDay()
    fun visitsByStatus(): List<VisitStatusReport> = visitRepository.visitsByStatus()

}