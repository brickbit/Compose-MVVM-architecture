package com.architecture.domain.useCase

import com.architecture.domain.model.CourseBo
import com.architecture.domain.repository.CourseRepository

class GetListCourseUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): Result<List<CourseBo>> {
        return repository.getCourseList()
            .onSuccess { return Result.success(it) }
            .onFailure { return Result.failure(it) }
    }
}