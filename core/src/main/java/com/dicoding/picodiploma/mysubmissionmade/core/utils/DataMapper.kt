package com.dicoding.picodiploma.mysubmissionmade.core.utils

import com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.entity.DetailMovieEntity
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.DetailMovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie

object DataMapper {
    fun mapFavoriteEntitiesToDomain(input: List<DetailMovieEntity>): List<DetailMovie> =
            input.map {
                DetailMovie(
                        movieId = it.movieId,
                        backdropPath = it.backdropPath,
                        posterPath = it.posterPath,
                        title = it.title,
                        voteAverage = it.voteAverage,
                        runtime = it.runtime,
                        releaseDate = it.releaseDate,
                        revenue = it.revenue,
                        budget = it.budget,
                        overview = it.overview,
                        isFavorite = it.isFavorite
                )
            }

    fun mapResponseToEntities(input: DetailMovieResponse) = DetailMovieEntity(
            movieId = input.id,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            title = input.title,
            voteAverage = input.voteAverage,
            runtime = input.runtime,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            budget = input.budget,
            overview = input.overview,
            isFavorite = false
    )

    fun mapEntitiesToDomain(input: DetailMovieEntity) = DetailMovie(
            movieId = input.movieId,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            title = input.title,
            voteAverage = input.voteAverage,
            runtime = input.runtime,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            budget = input.budget,
            overview = input.overview,
            isFavorite = input.isFavorite
    )

    fun mapDomainToEntity(input: DetailMovie) = DetailMovieEntity(
            movieId = input.movieId,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            title = input.title,
            voteAverage = input.voteAverage,
            runtime = input.runtime,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            budget = input.budget,
            overview = input.overview,
            isFavorite = input.isFavorite
    )
}