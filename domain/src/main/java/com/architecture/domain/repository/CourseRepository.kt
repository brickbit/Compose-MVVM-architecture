package com.architecture.domain.repository

import com.architecture.domain.model.CourseBo
import com.architecture.domain.model.CourseDetailBo

interface CourseRepository {
    suspend fun getCourseList(): Result<List<CourseBo>>
    suspend fun getCourseDetail(link: String): Result<CourseDetailBo>
}