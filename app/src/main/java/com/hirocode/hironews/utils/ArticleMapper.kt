package com.hirocode.hironews.utils

import com.hirocode.hironews.data.source.remote.response.ArticlesItem
import com.hirocode.hironews.domain.model.Article

object ArticleMapper {
    fun mapResponsesToDomain(input: List<ArticlesItem>): List<Article> {
        val articleList = ArrayList<Article>()
        input.map {
            val article = Article(
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
            )
            articleList.add(article)
        }
        return articleList
    }
}