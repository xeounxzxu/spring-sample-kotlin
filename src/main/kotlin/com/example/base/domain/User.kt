package com.example.base.domain

import javax.persistence.*

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = true, unique = true)
    var loginId: String? = null
}
