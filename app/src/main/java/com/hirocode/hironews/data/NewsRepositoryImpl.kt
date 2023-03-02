package com.hirocode.hironews.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hirocode.hironews.data.source.remote.NewsDataSource
import com.hirocode.hironews.data.source.remote.NewsPagingSource
import com.hirocode.hironews.data.source.remote.network.ApiService
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.domain.repository.NewsRepository
import com.hirocode.hironews.utils.ArticleMapper
import com.hirocode.hironews.utils.ArticlePagingMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
    private val apiService: ApiService,
) : NewsRepository {
    override fun getTopHeadlines(): Flow<List<Article>> {
        return newsDataSource.getTopHeadlines().map {
            ArticleMapper.mapResponsesToDomain(it)
        }
    }

    override fun getEverything(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService)
            }
        ).flow.map { pagingData -> pagingData.map { ArticlePagingMapper.mapResponsesToDomain(it) } }
    }
}