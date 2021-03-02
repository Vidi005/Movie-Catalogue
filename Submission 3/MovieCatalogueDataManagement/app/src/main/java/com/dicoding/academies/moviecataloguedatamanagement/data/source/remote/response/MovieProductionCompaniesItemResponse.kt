package com.dicoding.academies.moviecataloguedatamanagement.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieProductionCompaniesItemResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("origin_country")
    val originCountry: String
)
