package com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote

import android.util.Log
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.network.ApiResponse
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.network.ApiService
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getNowPlaying(): Flow<List<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getNowPlaying()
                val dataArray = response.results
                emit(dataArray)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getUpcoming(): Flow<List<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getUpcoming()
                val dataArray = response.results
                emit(dataArray)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSearch(query: String): Flow<List<MovieResponse>> {
        return  flow {
            try {
                val response = apiService.getSearchMovie(query)
                val dataArray = response.results
                emit(dataArray)
            } catch (e : Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId)
                val dataArray = response
                if (dataArray.title.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}