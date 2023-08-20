package com.example.carlos.basicmvckotlin.domain.entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
data class Car(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var name: String? = null,
        var model: String? = null,
        var velocity: Int? = null,
        @Column(name = "oil")
        var isOil: Boolean? = null,
        var color: String? = null,
        @CreationTimestamp
        @Column(name = "created_time")
        var createdTime: LocalDateTime? = null
)