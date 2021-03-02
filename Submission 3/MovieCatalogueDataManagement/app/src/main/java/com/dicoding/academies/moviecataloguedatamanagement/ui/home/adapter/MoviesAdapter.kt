package com.dicoding.academies.moviecataloguedatamanagement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.ItemListMovieBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback.OnMovieItemClickCallback

class MoviesAdapter(private val callback: OnMovieItemClickCallback) :
    PagedListAdapter<MovieEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemListMovieBinding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    inner class MoviesViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemMovieName.text = movie.title
                tvItemOverview.text = movie.overview
                tvItemRelease.text = movie.releaseDate
                Glide.with(itemView.context)
                    .load("${itemView.context
                        .getString(R.string.poster_base_url)}${movie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                    .error(R.drawable.ic_image_error)
                    .into(rivItemPoster)
                itemView.setOnClickListener { callback.onItemClicked(movie) }
                btnShare.setOnClickListener { callback.onItemShared(movie) }
            }
        }
    }
}