package com.dicoding.picodiploma.mysubmissionmade.core.domain.usecase

import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie
import com.dicoding.picodiploma.mysubmissionmade.core.domain.repository.IMovieRepository

class MovieInteractor(
        private val movieRepository: IMovieRepository
) : MovieUseCase {

    override fun getNowPlayingMovie() =  movieRepository.getNowPlayingMovie()

    override fun getUpcomingMovie() = movieRepository.getUpcomingMovie()

    override fun getSearchMovie(query: String) = movieRepository.getSearchMovie(query)

    override fun getDetailMovie(movieId: Int) = movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(detailMovie: DetailMovie, state: Boolean) = movieRepository.setFavoriteMovie(detailMovie, state)

}