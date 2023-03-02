package com.hirocode.hironews.di

import com.hirocode.hironews.domain.usecase.GetEverythingUseCase
import com.hirocode.hironews.domain.usecase.GetTopHeadlinesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetTopHeadlinesUseCase(get()) }
    factory { GetEverythingUseCase(get()) }
}