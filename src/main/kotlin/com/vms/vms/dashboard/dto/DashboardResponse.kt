package com.vms.vms.dashboard.dto

data class DashboardResponse(
    val totalVisitors: Long,
    val totalVisits: Long,
    val pendingVisits: Long,
    val approvedVisits: Long,
    val rejectedVisits: Long,
    val checkedInVisits: Long,
    val checkOutVisits: Long,
    val canceledVisits: Long,
    val todayVisits: Long
)