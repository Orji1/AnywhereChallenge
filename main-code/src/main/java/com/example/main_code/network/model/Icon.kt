package com.example.main_code.network.model


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("Height")
    val height: String? = null,
    @SerializedName("URL")
    val uRL: String? = null,
    @SerializedName("Width")
    val width: String? = null
)