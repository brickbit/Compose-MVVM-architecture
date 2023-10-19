package com.architecture.data.dataSource

import com.architecture.data.api.CourseApi
import com.architecture.data.constants.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(): CourseApi {
    val okhttp = OkHttpClient.Builder().build()
    val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()
    return retrofit.create(CourseApi::class.java)

}

