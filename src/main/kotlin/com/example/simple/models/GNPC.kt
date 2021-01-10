package com.example.simple.models

import javax.persistence.*

@Entity
class GNPC {

    @Id
    @GeneratedValue
    var id: Long? = null
    var nName: String? = null
    var nWeaponSkill: Long? = null
    var nArmourSkill: Long? = null
    var nHealth: Long? = null

    @OneToMany
    var nEquipment: Set<GItem>? = null

    @OneToMany
    var nInventory: Set<GConsumables>? = null

    @OneToMany
    var nEffects: Set<GEffect>? = null

}