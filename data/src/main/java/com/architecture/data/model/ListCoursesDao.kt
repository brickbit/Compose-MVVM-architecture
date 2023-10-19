package com.architecture.data.model

import com.google.gson.annotations.SerializedName

data class ListCoursesDao(
    @SerializedName("data")
    val data: List<CourseDao?>
)
