package com.dicoding.academies.moviecataloguetmdb.ui.callback

import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity

interface OnTVShowItemClickCallback {
    fun onItemClicked(data: TVShowEntity)
    fun onItemShared(data: TVShowEntity)
}
