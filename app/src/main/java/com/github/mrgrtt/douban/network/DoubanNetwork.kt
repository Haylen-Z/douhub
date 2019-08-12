package com.github.mrgrtt.douban.network

import com.github.mrgrtt.douban.model.Movie
import com.github.mrgrtt.douban.model.Novel
import com.github.mrgrtt.douban.model.NovelBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Create by mrgrtt on 2019.8.8
 */
object DoubanNetwork {
    suspend fun getVideos(type: String,
                  tag: String,
                  page_limit: Int,
                  page_start: Int): List<Movie> {
        val moviesResponse = withContext(Dispatchers.IO) {
            ServiceFactory.movieService
                .getVideos(type, tag, page_limit, page_start).await()
        }
        return moviesResponse.subjects
    }

    suspend fun getTags(type: String):List<String> {
        val tagResponse = withContext(Dispatchers.IO) {
            ServiceFactory.movieService.getTags(type).await()
        }
        return tagResponse.tags
    }

    suspend fun getNovels(sort: String, page: Int): List<Novel> {
        val body = NovelBody()
        body.sort = sort
        body.page = page
        val novelResponse = withContext(Dispatchers.IO) {
            ServiceFactory.readService.getNovels(body).await()
        }
        return novelResponse.list
    }

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }
}