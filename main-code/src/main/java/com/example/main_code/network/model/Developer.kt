package com.example.main_code.network.model


import com.google.gson.annotations.SerializedName

data class Developer(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)