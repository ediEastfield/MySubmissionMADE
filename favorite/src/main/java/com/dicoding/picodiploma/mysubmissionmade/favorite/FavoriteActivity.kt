package com.dicoding.picodiploma.mysubmissionmade.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubmissionmade.core.ui.FavoriteAdapter
import com.dicoding.picodiploma.mysubmissionmade.detail.DetailMovieActivity
import com.dicoding.picodiploma.mysubmissionmade.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Movie Time"

        getFavoriteData()
    }

    private fun getFavoriteData() {

        val favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.onItemClick = { favoriteMovie ->
            val detailFavorite = Intent(this, DetailMovieActivity::class.java)
            detailFavorite.putExtra(DetailMovieActivity.EXTRA_DATA, favoriteMovie.movieId)
            startActivity(detailFavorite)
        }

        favoriteViewModel.favoriteMovie.observe(this, { dataFavorite ->
            favoriteAdapter.setData(dataFavorite)
            binding.viewEmpty.visibility = if (dataFavorite.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvFavorite) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }
}