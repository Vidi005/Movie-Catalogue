package com.dicoding.academies.moviecataloguetmdb.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguetmdb.R
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.ItemListMovieBinding
import com.dicoding.academies.moviecataloguetmdb.ui.callback.OnMovieItemClickCallback

class MoviesAdapter(private val listMovies: ArrayList<MovieEntity>,
    private val callback: OnMovieItemClickCallback) :
        RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    fun setMovies(moviesItem: List<MovieEntity>?) {
        if (moviesItem.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(moviesItem)
        notifyDataSetChanged()
        Log.d("MoviesAdapter", "$listMovies")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemListMovieBinding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(listMovies[position])

    override fun getItemCount(): Int = listMovies.size

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