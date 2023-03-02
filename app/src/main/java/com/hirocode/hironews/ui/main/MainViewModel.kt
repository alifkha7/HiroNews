package com.hirocode.hironews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hirocode.hironews.domain.usecase.GetEverythingUseCase
import com.hirocode.hironews.domain.usecase.GetTopHeadlinesUseCase
import com.hirocode.hironews.ui.common.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    getEverythingUseCase: GetEverythingUseCase
) : ViewModel() {
    private val _newsState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newsState

    val newsPaging = getEverythingUseCase.invoke().cachedIn(viewModelScope)

    fun getTopHeadlines() {
        viewModelScope.launch {
            getTopHeadlinesUseCase.invoke()
                .onStart {
                    _newsState.value = NewsState.Loading
                }
                .catch {
                    _newsState.value = NewsState.Error(it.message.toString())
                }
                .collect { article ->
                    _newsState.value = NewsState.Success(article)
                }
        }
    }
}