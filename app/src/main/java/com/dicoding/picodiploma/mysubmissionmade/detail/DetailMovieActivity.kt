package com.dicoding.picodiploma.mysubmissionmade.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubmissionmade.R
import com.dicoding.picodiploma.mysubmissionmade.core.data.Resource
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie
import com.dicoding.picodiploma.mysubmissionmade.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_DATA, 0)

        detailMovieViewModel.getDetailMovie(movieId).observe(this, { detailMovie ->
            if (detailMovie != null) {
                when (detailMovie) {
                    is Resource.Loading -> binding.content.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.content.progressBar.visibility = View.GONE
                        showDetailMovie(detailMovie.data)
                    }
                    is Resource.Error -> {
                        binding.content.progressBar.visibility = View.GONE
                        binding.content.viewError.root.visibility = View.VISIBLE
                        binding.content.viewError.tvError.text =
                            detailMovie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovie(detailMovie: DetailMovie?) {
        detailMovie?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + detailMovie.backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.content.ivDetailBackdrop)

            binding.content.tvDetailTitle.text = detailMovie.title
            binding.content.tvDetailVoteAverage.text = detailMovie.voteAverage.toString()
            binding.content.tvDetailRuntime.text = detailMovie.runtime.toString() + "m"

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + detailMovie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.content.ivDetailPoster)

            binding.content.tvDetailReleaseDate.text = detailMovie.releaseDate
            binding.content.tvDetailRevenue.text = "Rp. " + detailMovie.revenue.toString()
            binding.content.tvDetailBudget.text = "Rp. " + detailMovie.budget.toString()
            binding.content.tvDetailDescription.text = detailMovie.overview


            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.content.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.content.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.content.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}