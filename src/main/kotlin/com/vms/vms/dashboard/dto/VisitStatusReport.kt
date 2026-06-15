package com.vms.vms.dashboard.dto

import com.vms.vms.visit.VisitStatus

data class VisitStatusReport (
    val status: VisitStatus,
    val total: Long
)