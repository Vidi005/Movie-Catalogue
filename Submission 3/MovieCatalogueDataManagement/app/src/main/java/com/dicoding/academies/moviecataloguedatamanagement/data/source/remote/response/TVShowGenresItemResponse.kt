package com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowGenresItemResponse(

    @field:SerializedName("name")
    val name: String
)
