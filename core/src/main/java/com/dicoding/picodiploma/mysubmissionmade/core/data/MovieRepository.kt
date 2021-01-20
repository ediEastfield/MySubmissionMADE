package com.dicoding.picodiploma.mysubmissionmade.core.data

import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.LocalDataSource
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.network.ApiResponse
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie
import com.dicoding.picodiploma.mysubmissionmade.core.domain.repository.IMovieRepository
import com.dicoding.picodiploma.mysubmissionmade.core.utils.AppExecutors
import com.dicoding.picodiploma.mysubmissionmade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getNowPlayingMovie(): Flow<List<MovieResponse>> {
        return remoteDataSource.getNowPlaying()
    }

    override fun getUpcomingMovie(): Flow<List<MovieResponse>> {
        return remoteDataSource.getUpcoming()
    }

    override fun getSearchMovie(query: String): Flow<List<MovieResponse>> {
        return remoteDataSource.getSearch(query)
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>> =
            object : NetworkBoundResource<DetailMovie, DetailMovieResponse>() {
                override fun loadFromDB(): Flow<DetailMovie> {
                    return localDataSource.getDetailMovie(movieId).map { detailMovie ->
                        if (detailMovie != null) {
                            DataMapper.mapEntitiesToDomain(detailMovie)
                        } else {
                            detailMovie
                        }
                    }
                }

                override fun shouldFetch(data: DetailMovie?): Boolean =
                        data?.title == null || data.title.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                        remoteDataSource.getDetailMovie(movieId)

                override suspend fun saveCallResult(data: DetailMovieResponse) {
                    val detailMovie = DataMapper.mapResponseToEntities(data)
                    localDataSource.insertDetailMovie(detailMovie)
                }

            }.asFlow()

    override fun getFavoriteMovie(): Flow<List<DetailMovie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapFavoriteEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(detailMovie: DetailMovie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(detailMovie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}