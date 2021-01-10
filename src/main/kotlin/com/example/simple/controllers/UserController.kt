package com.example.simple.controllers

import com.example.simple.models.GUser
import com.example.simple.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private val repository: UserRepository? = null

    @CrossOrigin
    @GetMapping("/getAll")
    fun getAll(): MutableIterable<GUser> {

        return repository!!.findAll()

    }

    @CrossOrigin
    @GetMapping("/get")
    fun get(@RequestParam id: String): GUser? {

        return repository!!.findByIdOrNull(id)

    }

    @CrossOrigin
    @PostMapping("/post")
    fun post(@RequestBody gUser: GUser): GUser {
        repository!!.save(gUser)
        return gUser

    }

    @CrossOrigin
    @DeleteMapping("/delete")
    fun delete(@RequestParam id: String): GUser? {

        val gUser: GUser? = repository!!.findByIdOrNull(id)
        if (gUser != null) {

            repository.delete(gUser)

        }

        return gUser
    }

}