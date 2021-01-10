package com.example.simple.models

import javax.persistence.*

@Entity
class User {

    @Id
    @GeneratedValue
    var id: Long? = null
    var userName: String? = null
    var email: String? = null


    @OneToOne(cascade = [CascadeType.ALL])
    var gCharacter: GCharacter? = null

    @ManyToMany
    var userAchievements: Set<GAchievements>? = null

}