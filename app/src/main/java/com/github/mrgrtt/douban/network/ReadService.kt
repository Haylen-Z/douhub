package com.github.mrgrtt.douban.network

import com.github.mrgrtt.douban.model.NovelBody
import com.github.mrgrtt.douban.model.NovelResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by mrgrtt on 2019.8.10
 */
interface ReadService {
    @Headers("Content-Type: application/json")
    @POST("j/kind/")
    fun getNovels(@Body body:NovelBody): Call<NovelResponse>
}