package com.example.simple.repositories

import com.example.simple.models.GEffect
import org.springframework.data.repository.CrudRepository

interface EffectRepository: CrudRepository<GEffect, Long>