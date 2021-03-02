package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.ItemListFavoriteMovieBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback.OnFavoriteMovieItemClickCallback

class FavoriteMoviesAdapter(private val callback: OnFavoriteMovieItemClickCallback) :
    PagedListAdapter<MovieEntity, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val itemListFavoriteMovieBinding =
            ItemListFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMoviesViewHolder(itemListFavoriteMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) =
        holder.bind(getItem(position) as MovieEntity)

    inner class FavoriteMoviesViewHolder(private val binding: ItemListFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemFavMovieName.text = movie.title
                tvItemFavOverview.text = movie.overview
                tvItemFavRelease.text = movie.releaseDate
                Glide.with(itemView.context)
                    .load("${itemView.context
                    .getString(R.string.poster_base_url)}${movie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                    .error(R.drawable.ic_image_error)
                    .into(rivItemFavPoster)
                itemView.setOnClickListener { callback.onItemClicked(movie) }
                btnFavShare.setOnClickListener { callback.onItemShared(movie) }
            }
        }
    }
}