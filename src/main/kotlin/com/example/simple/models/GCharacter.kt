package com.example.simple.models

import javax.persistence.*

@Entity
class GCharacter {

    @Id
    @GeneratedValue
    var id: Long? = null
    var cweaponskill: Long? = null
    var carmourskill: Long? = null
    var chealth: Long? = null
    var cstamina: Long? = null
    var caction: Int? = null
    var ccriticalcooldown: Int? = null
    var cbleedcooldown: Int? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var cweapon: GItem? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var carmour: GItem? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var cpotions: GConsumables? = null

}