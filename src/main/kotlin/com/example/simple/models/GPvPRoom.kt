package com.example.simple.models

import javax.persistence.*

@Entity
class GPvPRoom {

    @Id
    @GeneratedValue
    var id: Long? = null
    var rname: String? = null

    @OneToOne
    var rfirstplayer: GUser? = null

    @OneToOne
    var rsecondplayer: GUser? = null

    var rturn: Int? = null
    var rfinished: Boolean = false
    var rdelete: Boolean = false
    var rstartroom: Boolean = false

}