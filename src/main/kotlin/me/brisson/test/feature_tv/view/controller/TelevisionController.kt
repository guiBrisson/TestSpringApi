package me.brisson.test.feature_tv.view.controller

import me.brisson.test.feature_tv.service.TelevisionService
import me.brisson.test.feature_tv.shared.TelevisionDto
import me.brisson.test.feature_tv.view.model.TelevisionBody
import me.brisson.test.feature_tv.view.model.TelevisionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tv")
class TelevisionController(
    private val service: TelevisionService,
) {
    @GetMapping
    fun getAllTelevisions(): List<TelevisionResponse> {
        val tvList = service.getAllTelevisions()
        val mappedTvList: MutableList<TelevisionResponse> = mutableListOf()

        tvList.forEach { tv ->
            mappedTvList.add(TelevisionResponse(tv))
        }
        return mappedTvList
    }

    @GetMapping(value = ["/{id}"])
    fun getTelevisionById(@PathVariable id: String): ResponseEntity<TelevisionResponse> {
        val tv = service.getTelevisionById(id)
        if (tv.isPresent) {
            val response = TelevisionResponse(tv.get())
            return ResponseEntity(response, HttpStatus.FOUND)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun addTelevision(@RequestBody television: TelevisionBody): TelevisionResponse {
        val tv = TelevisionDto(television)
        val tvDto = service.addTelevision(tv)
        return TelevisionResponse(tvDto)
    }

    @PutMapping(value = ["/{id}"])
    fun editTelevision(@PathVariable id: String, @RequestBody television: TelevisionBody): ResponseEntity<TelevisionResponse> {
        val mapToTelevision = TelevisionDto(television, id)
        val tv = service.editTelevision(id, mapToTelevision)

        if (tv.isPresent) {
            val response = TelevisionResponse(tv.get())
            return ResponseEntity(response, HttpStatus.FOUND)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteTelevision(@PathVariable id: String): ResponseEntity<String> {
        return service.deleteTelevisionById(id)
    }
}