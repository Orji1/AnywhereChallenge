package com.example.main_code.network.model


import com.google.gson.annotations.SerializedName

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String? = null,
    @SerializedName("Icon")
    val icon: Icon? = null,
    @SerializedName("Result")
    val result: String? = null,
    @SerializedName("Text")
    val text: String? = null
)