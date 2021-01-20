package com.dicoding.picodiploma.mysubmissionmade.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.picodiploma.mysubmissionmade.core.ui.MovieAdapter
import com.dicoding.picodiploma.mysubmissionmade.databinding.FragmentSearchBinding
import com.dicoding.picodiploma.mysubmissionmade.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchMovieViewModel: SearchMovieViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val searchMovieAdapter = MovieAdapter()
            searchMovieAdapter.onItemClick = { searchMovie ->
                val detailMovie = Intent(activity, DetailMovieActivity::class.java)
                detailMovie.putExtra(DetailMovieActivity.EXTRA_DATA, searchMovie.id)
                startActivity(detailMovie)
            }

            var query = ""
            binding.ibSearch.setOnClickListener {
                query = binding.edtSearch.text.toString()
                if (query.isEmpty()) return@setOnClickListener

                binding.progressBar.visibility = View.VISIBLE
                searchMovieViewModel.getSearchMovie(query)
                    .observe(viewLifecycleOwner, { searchMovie ->
                        binding.progressBar.visibility = View.GONE
                        searchMovieAdapter.setData(searchMovie)
                        binding.viewEmpty.root.visibility =
                            if (searchMovie.isNotEmpty()) View.GONE else View.VISIBLE
                    })
            }

            with(binding.rvSearch) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = searchMovieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}