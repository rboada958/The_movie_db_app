package com.example.the_movie_db_app.app.base.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Romel Boada on 05/06/19.
 */


data class BaseResponse<T>(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<T?>? = null,

	@field:SerializedName("dates")
	val dates: Dates? = null
)