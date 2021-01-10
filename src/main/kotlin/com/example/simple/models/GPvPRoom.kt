package com.example.simple.models

import javax.persistence.*

@Entity
class GRoom {

    @Id
    @GeneratedValue
    var id: Long? = null
    var roomType: String? = null
    var roomName: String? = null

    @OneToMany
    var users: Set<User>? = null

    var turn: Long? = null

}