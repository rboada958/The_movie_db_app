package com.example.the_movie_db_app.ui.movies.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Romel Boada on 05/06/19.
 */

data class MovieResult(

	@field:SerializedName("vote_count")
	val voteCount: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("video")
	val video: Boolean? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null

)