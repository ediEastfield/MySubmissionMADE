package com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("backdrop_path")
        val backdropPath: String,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double,

        @field:SerializedName("runtime")
        val runtime: Int,

        @field:SerializedName("release_date")
        val releaseDate: String,

        @field:SerializedName("revenue")
        val revenue: Int,

        @field:SerializedName("budget")
        val budget: Int,

        @field:SerializedName("overview")
        val overview: String

)
