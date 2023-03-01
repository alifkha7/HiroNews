package com.hirocode.hironews.domain.model

data class Article(
    val title: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
)
