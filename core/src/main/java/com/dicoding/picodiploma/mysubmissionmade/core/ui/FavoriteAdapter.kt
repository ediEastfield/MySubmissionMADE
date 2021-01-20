package com.dicoding.picodiploma.mysubmissionmade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubmissionmade.core.R
import com.dicoding.picodiploma.mysubmissionmade.core.databinding.ItemListFavoriteBinding
import com.dicoding.picodiploma.mysubmissionmade.core.domain.model.DetailMovie

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder> () {

    private var listData = ArrayList<DetailMovie>()
    var onItemClick: ((DetailMovie) -> Unit)? = null

    fun setData(newListData: List<DetailMovie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder =
            ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_favorite, viewGroup, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(listViewHolder: ListViewHolder, position: Int) {
        val data = listData[position]
        listViewHolder.bind(data)
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListFavoriteBinding.bind(itemView)
        fun bind(data: DetailMovie) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w185" + data.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivItemFavorite)

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