package com.hirocode.hironews.utils

import com.hirocode.hironews.data.source.remote.response.ArticlesItem
import com.hirocode.hironews.domain.model.Article

object ArticlePagingMapper {
    fun mapResponsesToDomain(input: ArticlesItem) = Article(
        title = input.title,
        url = input.url,
        urlToImage = input.urlToImage,
        publishedAt = input.publishedAt,
    )
}