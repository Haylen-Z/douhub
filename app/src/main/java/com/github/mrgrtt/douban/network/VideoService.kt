package com.github.mrgrtt.douban.network

import com.github.mrgrtt.douban.model.MoviesResponse
import com.github.mrgrtt.douban.model.TagResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Create by mrgrtt on 2019.8.8
 */
interface VideoService {
    @GET("j/search_subjects")
    fun getVideos(@Query("type") type: String,
                  @Query("tag") tag: String,
                  @Query("page_limit") page_limit: Int,
                  @Query("page_start") page_start: Int): Call<MoviesResponse>

    @GET("j/search_tags")
    fun getTags(@Query("type") type: String): Call<TagResponse>
}