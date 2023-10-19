package com.architecture.data.model

import com.architecture.domain.model.CourseEpisodeBo
import com.google.gson.annotations.SerializedName

data class CourseItemDao(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val data: CourseAttributesDao
)


fun CourseItemDao.toBo(): CourseEpisodeBo {
    return CourseEpisodeBo(
        title = data.name ?: "",
        description = data.formattedDescription ?: data.description ?: ""
    )
}