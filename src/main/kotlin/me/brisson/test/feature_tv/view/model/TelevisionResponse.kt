package me.brisson.test.feature_tv.view.model

import me.brisson.test.feature_tv.shared.TelevisionDto

data class TelevisionResponse(
    var id: String,
    val model: String,
    val color: String,
    val manufacturer: String,
    val year: String,
    val resolution: String,
    val screenRatio: String,
    val screenSize: Int
) {
    constructor(tvDto: TelevisionDto): this(
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