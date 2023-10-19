package com.architecture.domain.useCase

import com.architecture.domain.model.CourseDetailBo
import com.architecture.domain.repository.CourseRepository

class GetDetailCourseUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(link:String): Result<CourseDetailBo> {
        return repository.getCourseDetail(link)
            .onSuccess { return Result.success(it) }
            .onFailure { return Result.failure(Error()) }
    }
}