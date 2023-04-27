package com.example.main_code.di

import com.example.main_code.network.ServiceApi
import com.example.main_code.network.ServiceApi.Companion.BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    val serviceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
            .create(ServiceApi::class.java)
    }

   private val okHttpClient: OkHttpClient by lazy {
       OkHttpClient.Builder()
           .addInterceptor(loggingInterceptor)
           .connectTimeout(30, TimeUnit.SECONDS)
           .readTimeout(30, TimeUnit.SECONDS)
           .writeTimeout(30, TimeUnit.SECONDS)
           .build()
   }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}