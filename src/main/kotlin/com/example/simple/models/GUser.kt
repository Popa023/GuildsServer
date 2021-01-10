package com.example.simple.models

import javax.persistence.*

@Entity
class GUser {

    @Id
    var id: String? = null
    var uname: String? = null
    var uemail: String? = null
    var ukingdom: String? = null
    var ukingdomFavor: Long? = null
    var uvictories: Long? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var uwarehouse: GWarehouse? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var ucharacter: GCharacter? = null

}