package me.brisson.test.feature_tv.shared

import me.brisson.test.feature_tv.model.Television
import me.brisson.test.feature_tv.view.model.TelevisionBody
import org.bson.types.ObjectId

data class TelevisionDto(
    var id: String,
    val model: String,
    val color: String,
    val manufacturer: String,
    val year: String,
    val resolution: String,
    val screenRatio: String,
    val screenSize: Int
) {
    constructor(tv: Television): this(
        id = tv.id,
        model = tv.model,
        color = tv.color,
        manufacturer = tv.manufacturer,
        year = tv.year,
        resolution = tv.resolution,
        screenRatio = tv.screenRatio,
        screenSize = tv.screenSize
    )

    constructor (tvBody: TelevisionBody, id: String? = ObjectId.get().toString()): this(
        id = id!!,
        model = tvBody.model,
        color = tvBody.color,
        manufacturer = tvBody.manufacturer,
        year = tvBody.year,
        resolution = tvBody.resolution,
        screenRatio = tvBody.screenRatio,
        screenSize = tvBody.screenSize
    )
}