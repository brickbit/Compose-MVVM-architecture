package com.architecture.domain.model

data class CourseDetailBo(
    val title: String,
    val description: String,
    val image: String,
    val content: List<CourseEpisodeBo>
)


