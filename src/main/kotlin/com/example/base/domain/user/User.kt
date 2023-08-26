package com.example.base.domain.user

import jakarta.persistence.*

@Entity(name = "USERS")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = true, unique = true)
    var loginId: String? = null
}
