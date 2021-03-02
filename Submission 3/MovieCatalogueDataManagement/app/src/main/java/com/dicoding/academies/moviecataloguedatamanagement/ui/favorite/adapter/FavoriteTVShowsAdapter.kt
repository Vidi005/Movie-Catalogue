package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.ItemListFavoriteTvShowBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback.OnFavoriteTVShowItemClickCallback

class FavoriteTVShowsAdapter(private val callback: OnFavoriteTVShowItemClickCallback) :
    PagedListAdapter<TVShowEntity,FavoriteTVShowsAdapter.FavoriteTVShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowsViewHolder {
        val itemListFavoriteTvShowBinding =
            ItemListFavoriteTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTVShowsViewHolder(itemListFavoriteTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTVShowsViewHolder, position: Int) {
        val favoriteTvShow = getItem(position)
        holder.bind(favoriteTvShow as TVShowEntity)
    }

    inner class FavoriteTVShowsViewHolder(private val binding: ItemListFavoriteTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemFavTvShowName.text = tvShow.name
                tvItemFavOverview.text = tvShow.overview
                tvItemFavYear.text = tvShow.firstAirDate
                Glide.with(itemView.context)
                    .load("${itemView.context
                        .getString(R.string.poster_base_url)}${tvShow.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                    .error(R.drawable.ic_image_error)
                    .into(rivItemFavPoster)
                itemView.setOnClickListener { callback.onItemClicked(tvShow) }
                btnFavShare.setOnClickListener { callback.onItemShared(tvShow) }
            }
        }
    }
}