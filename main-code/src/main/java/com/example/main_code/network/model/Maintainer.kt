package com.example.main_code.network.model


import com.google.gson.annotations.SerializedName

data class Maintainer(
    @SerializedName("github")
    val github: String? = null
)