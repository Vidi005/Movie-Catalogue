package com.dicoding.picodiploma.moviecatalogue.ui.callback

import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity

interface OnTVShowItemClickCallback {
    fun onItemClicked(data: TVShowEntity)
    fun onItemShared(data: TVShowEntity)
}
