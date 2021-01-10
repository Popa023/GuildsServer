package com.example.simple.controllers

import com.example.simple.models.GCharacter
import com.example.simple.repositories.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/characters")
class CharacterController {

    @Autowired
    private val repository: CharacterRepository? = null

    @CrossOrigin
    @GetMapping("/getAll")
    fun getAll(): MutableIterable<GCharacter> {

        return repository!!.findAll()

    }

    @CrossOrigin
    @GetMapping("/get")
    fun get(@RequestParam id: Long): GCharacter? {

        return repository!!.findByIdOrNull(id)

    }

    @CrossOrigin
    @PostMapping("/post")
    fun post(@RequestBody gCharacter: GCharacter): GCharacter {

        repository!!.save(gCharacter)
        return gCharacter

    }

    @CrossOrigin
    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): GCharacter? {

        val gCharacter: GCharacter? = repository!!.findByIdOrNull(id)
        if (gCharacter != null) {

            repository.delete(gCharacter)

        }

        return gCharacter
    }

}