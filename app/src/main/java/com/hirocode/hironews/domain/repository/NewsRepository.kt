package com.hirocode.hironews.domain.repository

import androidx.paging.PagingData
import com.hirocode.hironews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(): Flow<List<Article>>
    fun getEverything(): Flow<PagingData<Article>>
}