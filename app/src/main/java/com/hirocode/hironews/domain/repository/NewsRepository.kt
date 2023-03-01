package com.hirocode.hironews.domain.repository

import com.hirocode.hironews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(): Flow<List<Article>>
}