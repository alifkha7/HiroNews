package com.hirocode.hironews.domain.usecase

import androidx.paging.PagingData
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetEverythingUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<PagingData<Article>> = newsRepository.getEverything()
}