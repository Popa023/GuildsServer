package com.example.simple.models

import javax.persistence.*

@Entity
class GGeneral {

    @Id
    @GeneratedValue
    var id: Long? = null
    var gName: String? = null
    var gKingdom: String? = null
    var gStrength: Long? = null

    @OneToOne
    var gNPC: GNPC? = null

}