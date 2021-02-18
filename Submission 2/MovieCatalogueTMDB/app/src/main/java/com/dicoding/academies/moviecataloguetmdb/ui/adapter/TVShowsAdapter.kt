package com.dicoding.academies.moviecataloguetmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguetmdb.R
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.ItemListTvShowBinding
import com.dicoding.academies.moviecataloguetmdb.ui.callback.OnTVShowItemClickCallback

class TVShowsAdapter(private val listTVShows: ArrayList<TVShowEntity>,
     private val callback: OnTVShowItemClickCallback) :
        RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    fun setTVShows(tvShowsItem: List<TVShowEntity>?) {
        if (tvShowsItem.isNullOrEmpty()) return
        listTVShows.apply {
            clear()
            addAll(tvShowsItem)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowsAdapter.TVShowsViewHolder {
        val itemListTVShowBinding =
            ItemListTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemListTVShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowsAdapter.TVShowsViewHolder, position: Int) =
        holder.bind(listTVShows[position])

    override fun getItemCount(): Int = listTVShows.size

    inner class TVShowsViewHolder(private val binding: ItemListTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemTvShowName.text = tvShow.name
                tvItemOverview.text = tvShow.overview
                tvItemYear.text = tvShow.firstAirDate?.take(4) ?: "-"
                Glide.with(itemView.context)
                    .load("${itemView.context
                        .getString(R.string.poster_base_url)}${tvShow.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                    .error(R.drawable.ic_image_error)
                    .into(rivItemPoster)
                itemView.setOnClickListener { callback.onItemClicked(tvShow) }
                btnShare.setOnClickListener { callback.onItemShared(tvShow) }
            }
        }
    }
}