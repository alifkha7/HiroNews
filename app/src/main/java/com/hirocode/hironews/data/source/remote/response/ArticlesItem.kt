package com.hirocode.hironews.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@field:SerializedName("urlToImage")
	val urlToImage: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,
)