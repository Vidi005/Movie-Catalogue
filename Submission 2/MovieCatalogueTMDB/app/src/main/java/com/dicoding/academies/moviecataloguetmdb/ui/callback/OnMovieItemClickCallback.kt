package com.dicoding.academies.moviecataloguetmdb.ui.callback

import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity

interface OnMovieItemClickCallback {
    fun onItemClicked(data: MovieEntity)
    fun onItemShared(data: MovieEntity)
}
