package com.dicoding.picodiploma.mysubmissionmade.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}