package com.hirocode.hironews.data

import com.hirocode.hironews.data.source.remote.NewsDataSource
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.domain.repository.NewsRepository
import com.hirocode.hironews.utils.ArticleMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
) : NewsRepository {
    override fun getTopHeadlines(): Flow<List<Article>> {
        return newsDataSource.getTopHeadlines().map {
            ArticleMapper.mapResponsesToDomain(it)
        }
    }
}