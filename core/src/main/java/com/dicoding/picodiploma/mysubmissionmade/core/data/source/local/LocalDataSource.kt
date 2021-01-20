package com.dicoding.picodiploma.mysubmissionmade.core.data.source.local

import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.entity.DetailMovieEntity
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getDetailMovie(movieId: Int): Flow<DetailMovieEntity> = movieDao.getDetailMovie(movieId)

    fun getFavoriteMovie(): Flow<List<DetailMovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertDetailMovie(detailMovieList: DetailMovieEntity) = movieDao.insertDetailMovie(detailMovieList)

    fun setFavoriteMovie(detailMovie: DetailMovieEntity, newState: Boolean) {
        detailMovie.isFavorite = newState
        movieDao.updateFavoriteMovie(detailMovie)
    }
}