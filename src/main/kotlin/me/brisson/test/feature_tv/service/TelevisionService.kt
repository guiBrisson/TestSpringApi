package me.brisson.test.feature_tv.service

import me.brisson.test.feature_tv.shared.TelevisionDto
import org.springframework.http.ResponseEntity
import java.util.Optional

interface TelevisionService {

    fun getAllTelevisions() : List<TelevisionDto>

    fun getTelevisionById(id : String) : Optional<TelevisionDto>

    fun addTelevision(television: TelevisionDto) : TelevisionDto

    fun editTelevision(id: String, television: TelevisionDto) : Optional<TelevisionDto>

    fun deleteTelevisionById(id : String) : ResponseEntity<String>
}