package com.dicoding.picodiploma.mysubmissionmade.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovie(
        val movieId: Int,
        val backdropPath: String,
        val posterPath: String,
        val title: String,
        val voteAverage: Double,
        val runtime: Int,
        val releaseDate: String,
        val revenue: Int,
        val budget: Int,
        val overview: String,
        val isFavorite: Boolean
) : Parcelable
