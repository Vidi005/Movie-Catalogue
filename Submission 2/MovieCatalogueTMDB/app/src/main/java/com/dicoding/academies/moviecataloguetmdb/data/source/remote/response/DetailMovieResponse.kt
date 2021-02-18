package com.dicoding.academies.moviecataloguetmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genres")
	val genres: List<MovieGenresItemResponse>,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<MovieProductionCompaniesItemResponse>,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("tagline")
	val tagline: String
)
