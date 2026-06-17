package com.vms.vms.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "visitors")
class Visitor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var phoneNumber: String,

    var company: String? = null,

    var identificationType: String? = null,

    var identificationNumber: String? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),
 )