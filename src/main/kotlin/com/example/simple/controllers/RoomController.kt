package com.example.simple.controllers

import com.example.simple.models.GEffect
import com.example.simple.models.GPvPRoom
import com.example.simple.models.GUser
import com.example.simple.repositories.EffectRepository
import com.example.simple.repositories.RoomRepository
import com.example.simple.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@RestController
@RequestMapping("/rooms")
class RoomController {

    @Autowired
    private val repository: RoomRepository? = null
    @Autowired
    private val uRepository: UserRepository? = null
    @Autowired
    private val eRepository: EffectRepository? = null

    @CrossOrigin
    @GetMapping("/start")
    fun start(@RequestParam roomId: Long): GPvPRoom? {

        val pvPRoom: GPvPRoom = repository!!.findByIdOrNull(roomId) ?: return null
        if(!pvPRoom.rstartroom) {
            pvPRoom.rturn = Random.nextInt(1, 3)
            if (pvPRoom.rturn!! == 1){
                pvPRoom.rfirstplayer!!.ucharacter!!.caction = 1
                pvPRoom.rsecondplayer!!.ucharacter!!.caction = 0
            }
            else {
                pvPRoom.rfirstplayer!!.ucharacter!!.caction = 0
                pvPRoom.rsecondplayer!!.ucharacter!!.caction = 1
            }
            pvPRoom.rfirstplayer!!.ucharacter!!.ccriticalcooldown = 0
            pvPRoom.rsecondplayer!!.ucharacter!!.ccriticalcooldown = 0
            pvPRoom.rfirstplayer!!.ucharacter!!.cpotions!!.cquantity = 1
            pvPRoom.rsecondplayer!!.ucharacter!!.cpotions!!.cquantity = 1
            pvPRoom.rfirstplayer!!.ucharacter!!.cbleedcooldown = 0
            pvPRoom.rsecondplayer!!.ucharacter!!.cbleedcooldown = 0
            pvPRoom.rfirstplayer!!.ucharacter!!.chealth = 100 + 100 * ( 1/10 * pvPRoom.rfirstplayer!!.ucharacter!!.carmour!!.itier!! +
                    1/4 * pvPRoom.rfirstplayer!!.ucharacter!!.carmour!!.iquality!! + 1/4 * pvPRoom.rfirstplayer!!.ucharacter!!.carmour!!.irarity!!
                    + 1/1000 * pvPRoom.rfirstplayer!!.ucharacter!!.carmourskill!!)
            pvPRoom.rsecondplayer!!.ucharacter!!.chealth = 100 + 100 * ( 1/10 * pvPRoom.rsecondplayer!!.ucharacter!!.carmour!!.itier!! +
                    1/4 * pvPRoom.rsecondplayer!!.ucharacter!!.carmour!!.iquality!! + 1/4 * pvPRoom.rsecondplayer!!.ucharacter!!.carmour!!.irarity!!
                    + 1/1000 * pvPRoom.rsecondplayer!!.ucharacter!!.carmourskill!!)
            pvPRoom.rstartroom = true
        }
        uRepository!!.save(pvPRoom.rfirstplayer!!)
        uRepository.save(pvPRoom.rsecondplayer!!)
        repository.save(pvPRoom)
        return pvPRoom
    }

    @CrossOrigin
    @GetMapping("/action")
    fun action(@RequestParam roomId: Long, @RequestParam initiatorId: String,
               @RequestParam targetId: String, @RequestParam actionType: String): GPvPRoom? {

        val pvPRoom: GPvPRoom = repository!!.findByIdOrNull(roomId) ?: return null
        val initiator: GUser = uRepository!!.findByIdOrNull(initiatorId) ?: return null
        val target: GUser = uRepository.findByIdOrNull(targetId) ?: return null

        if (initiator.ucharacter!!.caction!! != 1) {
            return null
        }

        if(actionType == "potion"){
            if (initiator.ucharacter!!.cpotions!!.cquantity !!> 0) {
                initiator.ucharacter!!.cpotions!!.cquantity = initiator.ucharacter!!.cpotions!!.cquantity!! - 1
                initiator.ucharacter!!.caction = initiator.ucharacter!!.caction!! - 1
                target.ucharacter!!.chealth = target.ucharacter!!.chealth!! + 50 + 5 * initiator.ucharacter!!.cpotions!!.ctier!!
            }
        }

        if (actionType == "attack") {
            initiator.ucharacter!!.cweaponskill = initiator.ucharacter!!.cweaponskill?.plus(50)
            initiator.ucharacter!!.caction = initiator.ucharacter!!.caction!! - 1
            target.ucharacter!!.carmourskill = target.ucharacter!!.carmourskill?.plus(50)
            target.ucharacter!!.chealth = target.ucharacter!!.chealth!! - 30 - 10 * (1/10 * initiator.ucharacter!!.cweapon!!.itier!! +
                    1/4 * initiator.ucharacter!!.cweapon!!.iquality!! + 1/4 * initiator.ucharacter!!.cweapon!!.irarity!!
                    + 1/1000 * initiator.ucharacter!!.cweaponskill!!)
        }

        if (actionType == "critical") {
            initiator.ucharacter!!.cweaponskill = initiator.ucharacter!!.cweaponskill?.plus(50)
            initiator.ucharacter!!.caction = initiator.ucharacter!!.caction!! - 1
            initiator.ucharacter!!.ccriticalcooldown = 2;
            target.ucharacter!!.carmourskill = target.ucharacter!!.carmourskill?.plus(50)
            target.ucharacter!!.chealth = target.ucharacter!!.chealth!! - (30 - 10 * (1/10 * initiator.ucharacter!!.cweapon!!.itier!! +
                    1/4 * initiator.ucharacter!!.cweapon!!.iquality!! + 1/4 * initiator.ucharacter!!.cweapon!!.irarity!!
                    + 1/1000 * initiator.ucharacter!!.cweaponskill!!)) * 2
        }
        if (actionType == "bleed") {
            initiator.ucharacter!!.cweaponskill = initiator.ucharacter!!.cweaponskill?.plus(50)
            initiator.ucharacter!!.caction = initiator.ucharacter!!.caction!! - 1
            initiator.ucharacter!!.cbleedcooldown = 2;
            target.ucharacter!!.carmourskill = target.ucharacter!!.carmourskill?.plus(50)
            target.ucharacter!!.chealth = (target.ucharacter!!.chealth!! - 30 - 10 * (1/10 * initiator.ucharacter!!.cweapon!!.itier!! +
                    1/4 * initiator.ucharacter!!.cweapon!!.iquality!! + 1/4 * initiator.ucharacter!!.cweapon!!.irarity!!
                    + 1/1000 * initiator.ucharacter!!.cweaponskill!!))
            val bleed: GEffect = GEffect()
            bleed.eduration = 2
            bleed.epower = 2 + 2 * (1/10 * initiator.ucharacter!!.cweapon!!.itier!! +
                    1/4 * initiator.ucharacter!!.cweapon!!.iquality!! + 1/4 * initiator.ucharacter!!.cweapon!!.irarity!!
                    + 1/1000 * initiator.ucharacter!!.cweaponskill!!)
            bleed.etype = "bleed"
            bleed.etarget = target.ucharacter
            eRepository!!.save(bleed)
        }
        uRepository.save(initiator)
        uRepository.save(target)
        return pvPRoom
    }

    @CrossOrigin
    @GetMapping("/endTurn")
    fun endTurn(@RequestParam roomId: Long, @RequestParam userId: String): GPvPRoom? {

        val pvPRoom: GPvPRoom = repository!!.findByIdOrNull(roomId) ?: return null
        val effects = eRepository!!.findAll()
        if(pvPRoom.rturn == 1 && pvPRoom.rfirstplayer!!.id == userId) {
            pvPRoom.rturn = 2
            pvPRoom.rsecondplayer!!.ucharacter!!.caction = 1;
            for (e in effects){
                val copy = e
                if (e.etarget!!.id == pvPRoom.rsecondplayer!!.ucharacter!!.id && e.etype == "bleed") {
                    pvPRoom.rsecondplayer!!.ucharacter!!.chealth = pvPRoom.rsecondplayer!!.ucharacter!!.chealth!! -
                            e.epower!!
                    e.eduration = e.eduration!! - 1
                    if (e.eduration == 0) {
                        eRepository.delete(copy)
                    }
                }
            }
            uRepository!!.save(pvPRoom.rsecondplayer!!)
        }
        else if(pvPRoom.rsecondplayer!!.id == userId) {
            pvPRoom.rturn = 1
            pvPRoom.rfirstplayer!!.ucharacter!!.caction = 1;
            for (e in effects){
                val copy = e
                if (e.etarget!!.id == pvPRoom.rfirstplayer!!.ucharacter!!.id && e.etype == "bleed") {
                    pvPRoom.rfirstplayer!!.ucharacter!!.chealth = pvPRoom.rfirstplayer!!.ucharacter!!.chealth!! -
                            e.epower!!
                    e.eduration = e.eduration!! - 1
                    if (e.eduration == 0) {
                        eRepository.delete(copy)
                    }
                }
            }
            uRepository!!.save(pvPRoom.rfirstplayer!!)
        }
        uRepository!!.save(pvPRoom.rfirstplayer!!)
        uRepository.save(pvPRoom.rsecondplayer!!)
        repository.save(pvPRoom)
        return pvPRoom

    }

    @CrossOrigin
    @GetMapping("endGame")
    fun endGame(@RequestParam roomId: Long): GPvPRoom? {

        val pvPRoom: GPvPRoom = repository!!.findByIdOrNull(roomId) ?: return null
        if (pvPRoom.rsecondplayer!!.ucharacter!!.chealth!! <= 0 && pvPRoom.rfinished == false) {
            pvPRoom.rfinished = true
            pvPRoom.rfirstplayer!!.uwarehouse!!.wiron = pvPRoom.rfirstplayer!!.uwarehouse!!.wiron!! + 1
            pvPRoom.rfirstplayer!!.uwarehouse!!.wironr = pvPRoom.rfirstplayer!!.uwarehouse!!.wironr!! + 1
            pvPRoom.rfirstplayer!!.uwarehouse!!.wherb = pvPRoom.rfirstplayer!!.uwarehouse!!.wherb!! + 1
            pvPRoom.rfirstplayer!!.uwarehouse!!.wherbr = pvPRoom.rfirstplayer!!.uwarehouse!!.wherbr!! + 1
            pvPRoom.rfirstplayer!!.uvictories = pvPRoom.rfirstplayer!!.uvictories!! + 1
        }
        if (pvPRoom.rfirstplayer!!.ucharacter!!.chealth!! <= 0 && pvPRoom.rfinished == false) {
            pvPRoom.rfinished = true
            pvPRoom.rsecondplayer!!.uwarehouse!!.wiron = pvPRoom.rsecondplayer!!.uwarehouse!!.wiron!! + 1
            pvPRoom.rsecondplayer!!.uwarehouse!!.wironr = pvPRoom.rsecondplayer!!.uwarehouse!!.wironr!! + 1
            pvPRoom.rsecondplayer!!.uwarehouse!!.wherb = pvPRoom.rsecondplayer!!.uwarehouse!!.wherb!! + 1
            pvPRoom.rsecondplayer!!.uwarehouse!!.wherbr = pvPRoom.rsecondplayer!!.uwarehouse!!.wherbr!! + 1
            pvPRoom.rsecondplayer!!.uvictories = pvPRoom.rsecondplayer!!.uvictories!! + 1
        }
        uRepository!!.save(pvPRoom.rfirstplayer!!)
        uRepository.save(pvPRoom.rsecondplayer!!)
        repository.save(pvPRoom)
        return pvPRoom
    }

    @CrossOrigin
    @GetMapping("/finishGame")
    fun finishGame(@RequestParam roomId: Long): GPvPRoom? {

        val pvPRoom: GPvPRoom = repository!!.findByIdOrNull(roomId) ?: return null
        if (!pvPRoom.rfinished) {
            pvPRoom.rfinished = true
        }
        else {
            repository.delete(pvPRoom)
        }
        return pvPRoom

    }

    @CrossOrigin
    @GetMapping("/getAll")
    fun getAll(): MutableIterable<GPvPRoom> {

        return repository!!.findAll()

    }

    @CrossOrigin
    @GetMapping("/get")
    fun get(@RequestParam id: Long): GPvPRoom? {

        return repository!!.findByIdOrNull(id)

    }

    @CrossOrigin
    @PostMapping("/post")
    fun post(@RequestBody gPvPRoom: GPvPRoom): GPvPRoom {
        return repository!!.save(gPvPRoom)
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): GPvPRoom? {

        val gPvPRoom: GPvPRoom? = repository!!.findByIdOrNull(id)
        if (gPvPRoom != null) {

            repository.delete(gPvPRoom)

        }

        return gPvPRoom
    }

}