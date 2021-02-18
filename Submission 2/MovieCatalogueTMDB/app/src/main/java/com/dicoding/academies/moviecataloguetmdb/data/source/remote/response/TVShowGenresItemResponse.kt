package com.dicoding.academies.moviecataloguetmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowGenresItemResponse(

    @field:SerializedName("name")
    val name: String
)
