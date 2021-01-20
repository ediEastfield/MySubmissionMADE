package com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase

import com.dicoding.picodiploma.mysubmissionmade.core.data.Resource
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getNowPlayingMovie(): Flow<List<MovieResponse>>

    fun getUpcomingMovie(): Flow<List<MovieResponse>>

    fun getSearchMovie(query: String): Flow<List<MovieResponse>>

    fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>>

    fun getFavoriteMovie(): Flow<List<DetailMovie>>

    fun setFavoriteMovie(detailMovie: DetailMovie, state: Boolean)

}