package com.dicoding.academies.moviecataloguetmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesItemResponse(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("id")
    val id: Int
)
