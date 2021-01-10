package com.example.simple.models

import javax.persistence.*

@Entity
class GMissionRoom {

    @Id
    @GeneratedValue
    var id: Long? = null
    var roomName: String? = null

    @OneToOne
    var mFirstPlayer: GUser? = null

    @OneToOne
    var mNPC: GNPC? = null

    var turn: Long? = null

}