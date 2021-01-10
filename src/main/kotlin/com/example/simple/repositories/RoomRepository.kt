package com.example.simple.repositories

import com.example.simple.models.GPvPRoom
import org.springframework.data.repository.CrudRepository

interface RoomRepository: CrudRepository<GPvPRoom, Long>