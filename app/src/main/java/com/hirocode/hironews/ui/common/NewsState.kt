package com.hirocode.hironews.ui.common

import com.hirocode.hironews.domain.model.Article

sealed class NewsState{
    object Loading : NewsState()
    data class Success(val data: List<Article>) : NewsState()
    data class Error(val message: String) : NewsState()
}
