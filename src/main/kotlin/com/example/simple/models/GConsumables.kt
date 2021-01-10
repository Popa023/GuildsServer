package com.example.simple.models

import javax.persistence.*

@Entity
class GConsumables {

    @Id
    @GeneratedValue
    var id: Long? = null
    var ctype: String? = null
    var ctier: Long? = null
    var cquality: String? = null
    var crarity: String? = null
    var cquantity: Long? = null

}