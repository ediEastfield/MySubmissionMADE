package com.dicoding.picodiploma.mysubmissionmade.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailMovie")
data class DetailMovieEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        var movieId: Int,

        @ColumnInfo(name = "backdrop_path")
        var backdropPath: String = "",

        @ColumnInfo(name = "poster_path")
        var posterPath: String = "",

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "vote_average")
        var voteAverage: Double = 0.0,

        @ColumnInfo(name = "runtime")
        var runtime: Int = 0,

        @ColumnInfo(name = "release_date")
        var releaseDate: String = "",

        @ColumnInfo(name = "revenue")
        var revenue: Int = 0,

        @ColumnInfo(name = "budget")
        var budget: Int = 0,

        @ColumnInfo(name = "overview")
        var overview: String = "",

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)
