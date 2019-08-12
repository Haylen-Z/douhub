package com.github.mrgrtt.douban.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by mrgrtt on 2019.8.8
 */
object ServiceFactory {
    val movieService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_MOVIE_URL)
            .build().create(VideoService::class.java)
    }

    val readService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_READ_URL)
            .build().create(ReadService::class.java)
    }
}