package com.dicoding.academies.moviecataloguetmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTVShowResponse(

	@field:SerializedName("genres")
	val genres: List<TVShowGenresItemResponse>,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<TVShowProductionCompaniesItemResponse>,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("tagline")
	val tagline: String
)

