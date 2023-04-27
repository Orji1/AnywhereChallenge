package com.example.main_code.network

import com.example.main_code.network.model.CharResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("/")
    suspend fun getCharacters(
        @Query("q") q: String,
        @Query("format") format: String = FORMAT
    ): Response<CharResponse>

    companion object {
        const val BASE_URL = "http://api.duckduckgo.com/"
        const val IMAGE_BASE_URL = "https://duckduckgo.com/"
        private const val FORMAT = "json"
    }
}