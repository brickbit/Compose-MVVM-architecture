package com.architecture.data.model

import com.architecture.domain.model.CourseBo
import com.google.gson.annotations.SerializedName

data class CourseDao(
    @SerializedName("id")
    val courseId: String,
    @SerializedName("attributes")
    val attributes: CourseAttributesDao,
    @SerializedName("links")
    val link: LinkDao,
)

fun CourseDao.toBo(): CourseBo {
    return CourseBo(
        id = courseId,
        title = attributes.name ?: "",
        description = attributes.formattedDescription ?: "",
        image = attributes.artworkUrl ?: "",
        link = link.link
    )
}
