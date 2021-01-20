package com.dicoding.picodiploma.mysubmissionmade.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie
import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetailMovie(movieId: Int) = movieUseCase.getDetailMovie(movieId).asLiveData()

    fun setFavoriteMovie(detailMovie: DetailMovie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(detailMovie, newStatus)
}