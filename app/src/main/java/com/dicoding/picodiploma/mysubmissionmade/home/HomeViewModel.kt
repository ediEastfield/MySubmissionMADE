package com.dicoding.picodiploma.mysubmissionmade.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val nowPlayingMovie = movieUseCase.getNowPlayingMovie().asLiveData()

    val upComingMovie = movieUseCase.getUpcomingMovie().asLiveData()

}