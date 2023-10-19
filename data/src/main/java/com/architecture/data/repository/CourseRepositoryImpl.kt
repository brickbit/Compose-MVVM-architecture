package com.architecture.data.repository

import com.architecture.data.api.CourseApi
import com.architecture.data.dataSource.makeRequest
import com.architecture.data.model.toBo
import com.architecture.domain.model.CourseBo
import com.architecture.domain.model.CourseDetailBo
import com.architecture.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val api: CourseApi
): CourseRepository {
    override suspend fun getCourseList(): Result<List<CourseBo>> {
        return makeRequest(
            apiRequest = { api.getCourses() },
            transform = { list -> list?.data?.mapNotNull { it?.toBo() } }
        )

    }

    override suspend fun getCourseDetail(link: String): Result<CourseDetailBo> {
        return makeRequest(
            apiRequest = { api.getCourseDetail(link) },
            transform = { it?.toBo() }
        )
    }
}