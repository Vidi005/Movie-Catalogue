package com.dicoding.academies.moviecataloguedatamanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.ItemListTvShowBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback.OnTVShowItemClickCallback

class TVShowsAdapter(private val callback: OnTVShowItemClickCallback
) : PagedListAdapter<TVShowEntity, TVShowsAdapter.TVShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowsViewHolder {
        val itemListTVShowBinding =
            ItemListTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemListTVShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let { holder.bind(it) }
    }

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