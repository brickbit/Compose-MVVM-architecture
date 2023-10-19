package com.architecture.data.api

import com.architecture.data.constants.Constant
import com.architecture.data.model.CourseDetailDao
import com.architecture.data.model.ListCoursesDao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CourseApi {

    @GET(Constant.GET_COURSES)
    suspend fun getCourses(
    ): Response<ListCoursesDao>

    @GET
    suspend fun getCourseDetail(
        @Url link: String
    ): Response<CourseDetailDao>
}