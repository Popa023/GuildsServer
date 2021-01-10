package com.example.simple.repositories

import com.example.simple.models.GBossRoom
import org.springframework.data.repository.CrudRepository

interface BossRoomRepository: CrudRepository<GBossRoom, Long>
