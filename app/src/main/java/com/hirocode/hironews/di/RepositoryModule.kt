package com.hirocode.hironews.di

import com.hirocode.hironews.data.NewsRepositoryImpl
import com.hirocode.hironews.data.source.remote.NewsDataSource
import com.hirocode.hironews.data.source.remote.NewsPagingSource
import com.hirocode.hironews.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsDataSource(get()) }
    single { NewsPagingSource(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}