package com.hirocode.hironews.domain.usecase

import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetTopHeadlinesUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> = newsRepository.getTopHeadlines()
}