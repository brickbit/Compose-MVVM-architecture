package com.architecture.data.model

import com.architecture.domain.model.CourseDetailBo
import com.google.gson.annotations.SerializedName

data class CourseDetailDao(
    @SerializedName("data")
    val data: CourseDetailDataDao,
    @SerializedName("included")
    val content: List<CourseItemDao>
)

data class CourseDetailDataDao(
    @SerializedName("attributes")
    val attributes: CourseAttributesDao
)

fun CourseDetailDao.toBo(): CourseDetailBo {
    return CourseDetailBo(
        title = data.attributes.name ?: "",
        description = data.attributes.formattedDescription ?: data.attributes.description ?: "",
        image = data.attributes.artworkUrl ?: "",
        content = content.map { dao ->
            dao.toBo()
        }
    )
}


