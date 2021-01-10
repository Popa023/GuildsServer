package com.example.simple.models

import javax.persistence.*

@Entity
class GEffect {

    @Id
    @GeneratedValue
    var id: Long? = null
    var etype: String? = null
    var epower: Long? = null
    var eduration: Int? = null

    @ManyToOne
    var etarget: GCharacter? = null

}