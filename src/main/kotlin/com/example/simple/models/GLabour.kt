package com.example.simple.models

import java.util.*
import javax.persistence.*

@Entity
class GLabour {

    @Id
    @GeneratedValue
    var id: Long? = null
    var lType: String? = null
    var lSkillProgress: Long? = null
    var lRestDate: Date? = null

}