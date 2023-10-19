package com.architecture.mvvm.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architecture.domain.error.ErrorType
import com.architecture.domain.error.getString
import com.architecture.domain.model.CourseBo
import com.architecture.domain.useCase.GetListCourseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListCourseViewModel(
    private val useCase: GetListCourseUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<CourseListState> =
        MutableStateFlow(CourseListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke()
                .onSuccess { courses ->
                    _state.update {
                        CourseListState.Success(courses)
                    }
                }.onFailure { error ->
                    _state.update {
                        CourseListState.Failure(
                            (error as? ErrorType)?.getString() ?: error.message
                            ?: error::class.java.name
                        )
                    }
                }
        }
    }
}

sealed class CourseListState {
    object Loading : CourseListState()
    data class Success(val list: List<CourseBo>) : CourseListState()
    data class Failure(val message: String) : CourseListState()
}