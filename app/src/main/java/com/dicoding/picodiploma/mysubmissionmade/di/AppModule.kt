package com.dicoding.picodiploma.mysubmissionmade.di

import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieInteractor
import com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase.MovieUseCase
import com.dicoding.picodiploma.mysubmissionmade.detail.DetailMovieViewModel
import com.dicoding.picodiploma.mysubmissionmade.home.HomeViewModel
import com.dicoding.picodiploma.mysubmissionmade.search.SearchMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchMovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}