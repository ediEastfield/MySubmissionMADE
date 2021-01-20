package com.dicoding.picodiploma.mysubmissionmade.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieUseCase

class SearchMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getSearchMovie(query: String) = movieUseCase.getSearchMovie(query).asLiveData()

}