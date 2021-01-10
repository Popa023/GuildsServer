package com.example.simple.models

import javax.persistence.*

@Entity
class GWarehouse {

    @Id
    @GeneratedValue
    var id: Long? = null
    var wiron: Long? = null
    var wironr: Long? = null
    var wherb: Long? = null
    var wherbr: Long? = null

}