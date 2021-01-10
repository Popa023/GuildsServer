package com.example.simple.repositories

import com.example.simple.models.GUser
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<GUser, String>