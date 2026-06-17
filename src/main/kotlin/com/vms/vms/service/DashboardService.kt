package com.vms.vms.service

import com.vms.vms.model.response.DailyVisitReport
import com.vms.vms.model.response.VisitStatusReport
import com.vms.vms.model.response.DashboardResponse
import com.vms.vms.repository.VisitRepository
import com.vms.vms.utility.VisitStatus
import com.vms.vms.repository.VisitorRepository
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