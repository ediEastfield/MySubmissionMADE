package com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.network

import com.dicoding.picodiploma.mysubmissionmade.core.BuildConfig
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = BuildConfig.LANGUAGE,
            @Query("page") page: Int = 1,
    ): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int = 1,
    ): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = BuildConfig.LANGUAGE,
    ): DetailMovieResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
            @Query("query") query: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = BuildConfig.LANGUAGE,
            @Query("page") page: Int = 1,
    ): ListMovieResponse
}