package com.architecture.mvvm.di

import com.architecture.data.dataSource.createRetrofit
import com.architecture.data.repository.CourseRepositoryImpl
import com.architecture.domain.repository.CourseRepository
import com.architecture.domain.useCase.GetDetailCourseUseCase
import com.architecture.domain.useCase.GetListCourseUseCase
import com.architecture.mvvm.screens.detail.CourseDetailViewModel
import com.architecture.mvvm.screens.list.ListCourseViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val apiModules = module {
    single { createRetrofit() }
}

val repositoryModules = module {
    factory<CourseRepository>{ CourseRepositoryImpl(get()) }
}

val useCaseModules = module {
    factory { GetListCourseUseCase(get()) }
    factory { GetDetailCourseUseCase(get()) }
}

val viewModelModules = module {
    viewModel { ListCourseViewModel(get()) }
    viewModel { CourseDetailViewModel(get()) }
}
