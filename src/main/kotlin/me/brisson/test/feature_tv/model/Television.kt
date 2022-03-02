package me.brisson.test.feature_tv.model

import me.brisson.test.feature_tv.shared.TelevisionDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tv")
data class Television(
    @Id
    var id: String,
    val model: String,
    val color: String,
    val manufacturer: String,
    val year: String,
    val resolution: String,
    val screenRatio: String,
    val screenSize: Int
) {
    constructor(tvDto: TelevisionDto) : this(
        id = tvDto.id,
        model = tvDto.model,
        color = tvDto.color,
        manufacturer = tvDto.manufacturer,
        year = tvDto.year,
        resolution = tvDto.resolution,
        screenRatio = tvDto.screenRatio,
        screenSize = tvDto.screenSize
    )
}
