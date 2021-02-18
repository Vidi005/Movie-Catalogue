package com.dicoding.academies.moviecataloguetmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowResponse(

    @field:SerializedName("results")
    val results: List<TVShowsItemResponse>
)
