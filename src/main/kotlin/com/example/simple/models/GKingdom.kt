package com.example.simple.models

import javax.persistence.*

@Entity
class GKingdom {

    @Id
    @GeneratedValue
    var id: Long? = null
    var kName: String? = null
    var kType: String? = null
    var kStrength: Long? = null
    var reward: Boolean? = null

}