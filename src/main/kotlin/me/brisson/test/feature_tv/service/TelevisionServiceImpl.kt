package me.brisson.test.feature_tv.service

import me.brisson.test.feature_tv.model.Television
import me.brisson.test.feature_tv.repository.TelevisionRepository
import me.brisson.test.feature_tv.shared.TelevisionDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TelevisionServiceImpl(private val repo: TelevisionRepository) : TelevisionService {

    override fun getAllTelevisions(): List<TelevisionDto> {
        val tvList: List<Television> = repo.findAll()
        val mappedTvList: MutableList<TelevisionDto> = mutableListOf()

        tvList.forEach { tv ->
            mappedTvList.add(TelevisionDto(tv))
        }
        return mappedTvList
    }

    override fun getTelevisionById(id: String): Optional<TelevisionDto> {
        val tvOptional = repo.findById(id)

        if (tvOptional.isPresent) {
            val tv = TelevisionDto(tvOptional.get())
            return Optional.of(tv)
        }

        return Optional.empty()
    }

    override fun addTelevision(television: TelevisionDto): TelevisionDto {
        val mapToTelevision = Television(television)
        val tv = repo.insert(mapToTelevision)
        return TelevisionDto(tv)
    }

    override fun editTelevision(id: String, television: TelevisionDto): Optional<TelevisionDto> {
        val optional = getTelevisionById(id)

        if (optional.isPresent) {
            val mapToTelevision = Television(television)
            mapToTelevision.id = id
            val tv = repo.save(mapToTelevision)
            return Optional.of(TelevisionDto(tv))
        }

        return Optional.empty()
    }

    override fun deleteTelevisionById(id: String): ResponseEntity<String> {
        val optional = getTelevisionById(id)

        if (optional.isPresent) {
            repo.deleteById(id)
             return ResponseEntity("Deleted successfully", HttpStatus.OK)
        }
        return ResponseEntity("Object not found, verify if the id is correct!", HttpStatus.NOT_FOUND)
    }
}