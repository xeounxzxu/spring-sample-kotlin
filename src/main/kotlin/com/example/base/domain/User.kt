package com.example.base.domain

import javax.persistence.*

@Entity
@Table(name = "k_user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "login_id", nullable = true)
    var loingId: String? = null

}
