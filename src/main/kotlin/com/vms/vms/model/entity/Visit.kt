package com.vms.vms.model.entity

import com.vms.vms.utility.VisitStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "visits")
class Visit(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id")
    var visitor: Visitor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    var host: User,

    @Column(nullable = false)
    var purpose: String,

    @Column(nullable = false)
    var visitDate: LocalDate,

    @Enumerated(EnumType.STRING)
    var status: VisitStatus = VisitStatus.PENDING,

    val createdAt: LocalDateTime = LocalDateTime.now(),
)