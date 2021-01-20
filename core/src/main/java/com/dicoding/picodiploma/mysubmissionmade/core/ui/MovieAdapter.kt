package com.dicoding.picodiploma.mysubmissionmade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubmissionmade.core.R
import com.dicoding.picodiploma.mysubmissionmade.core.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.mysubmissionmade.core.databinding.ItemListMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieResponse>()
    var onItemClick: ((MovieResponse) -> Unit)? = null

    fun setData(newListData: List<MovieResponse>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder =
            ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_movie, viewGroup, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(listViewHolder: ListViewHolder, position: Int) {
        val data = listData[position]
        listViewHolder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: MovieResponse) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w185" + data.posterPath)
                        .centerCrop()
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivItemMoviePopular)

                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.voteAverage.toString()

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}