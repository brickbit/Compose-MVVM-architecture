package com.architecture.mvvm.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architecture.domain.error.ErrorType
import com.architecture.domain.error.getString
import com.architecture.domain.model.CourseDetailBo
import com.architecture.domain.useCase.GetDetailCourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val useCase: GetDetailCourseUseCase,
): ViewModel() {

    private val _state: MutableStateFlow<CourseDetailState> = MutableStateFlow(CourseDetailState.Loading)
    val state = _state.asStateFlow()

    fun loadDetail(link: String) {
        viewModelScope.launch {
            useCase.invoke(link)
                .onSuccess { course ->
                    _state.update {
                        CourseDetailState.Success(course)
                    }
                }
                .onFailure { error ->
                    _state.update {
                        CourseDetailState.Failure(
                            (error as? ErrorType)?.getString() ?: error.message
                            ?: error::class.java.name
                        )
                    }
                }
        }
    }
}

sealed class CourseDetailState {
    object Loading: CourseDetailState()
    data class Success(val course: CourseDetailBo): CourseDetailState()
    data class Failure(val message: String): CourseDetailState()
}