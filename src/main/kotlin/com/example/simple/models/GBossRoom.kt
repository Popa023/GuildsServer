package com.example.simple.models

import javax.persistence.*

@Entity
class GBossRoom {

    @Id
    @GeneratedValue
    var id: Long? = null
    var roomName: String? = null

    @OneToOne
    var rFirstPlayer: GUser? = null

    @OneToOne
    var rSecondUser: GGeneral? = null

    var turn: Long? = null

}
