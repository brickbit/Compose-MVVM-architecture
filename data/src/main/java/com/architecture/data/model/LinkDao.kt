package com.architecture.data.model

import com.google.gson.annotations.SerializedName

data class LinkDao(
    @SerializedName("self")
    val link: String,
)

