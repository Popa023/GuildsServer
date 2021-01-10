package com.example.simple.models

import javax.persistence.*

@Entity
class GItem {

    @Id
    @GeneratedValue
    var id: Long? = null
    var itype: String? = null
    var itier: Long? = null
    var iquality: Long? = null
    var irarity: Long? = null
    var idurability: Long? = null

}