package com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.room

import androidx.room.*
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.entity.DetailMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM detailMovie WHERE movieId = :movieId")
    fun getDetailMovie(movieId: Int): Flow<DetailMovieEntity>

    @Query("SELECT * FROM detailMovie WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<DetailMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovie: DetailMovieEntity)

    @Update
    fun updateFavoriteMovie(detailMovie: DetailMovieEntity)
}