package com.dicoding.picodiploma.mysubmissionmade.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.dicoding.picodiploma.mysubmissionmade.R
import com.dicoding.picodiploma.mysubmissionmade.core.ui.MovieAdapter
import com.dicoding.picodiploma.mysubmissionmade.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.mysubmissionmade.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.amazon))
        imageList.add(SlideModel(R.drawable.disney))
        imageList.add(SlideModel(R.drawable.hbo))
        imageList.add(SlideModel(R.drawable.iflix))
        imageList.add(SlideModel(R.drawable.netflix))
        imageList.add(SlideModel(R.drawable.viu))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        if (activity != null) {

            val nowPlayingMovieAdapter = MovieAdapter()
            val upcomingMovieAdapter = MovieAdapter()

            nowPlayingMovieAdapter.onItemClick = { nowPlaying ->
                val detailNowPlaying = Intent(activity, DetailMovieActivity::class.java)
                detailNowPlaying.putExtra(DetailMovieActivity.EXTRA_DATA, nowPlaying.id)
                startActivity(detailNowPlaying)
            }

            upcomingMovieAdapter.onItemClick = { upComing ->
                val detailUpComing = Intent(activity, DetailMovieActivity::class.java)
                detailUpComing.putExtra(DetailMovieActivity.EXTRA_DATA, upComing.id)
                startActivity(detailUpComing)
            }

            binding.progressBar.visibility = View.VISIBLE
            homeViewModel.nowPlayingMovie.observe(viewLifecycleOwner, Observer { nowPlaying ->
                if (nowPlaying != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.tvNowPlaying.visibility = View.VISIBLE
                    nowPlayingMovieAdapter.setData(nowPlaying)
                }
            })

            homeViewModel.upComingMovie.observe(viewLifecycleOwner, Observer { upcoming ->
                if (upcoming != null) {
                    binding.tvUpcomingMovie.visibility = View.VISIBLE
                    upcomingMovieAdapter.setData(upcoming)
                }
            })

            with(binding.rvNowPlayingMovie) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = nowPlayingMovieAdapter
            }

            with(binding.rvUpcomingMovie) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingMovieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}