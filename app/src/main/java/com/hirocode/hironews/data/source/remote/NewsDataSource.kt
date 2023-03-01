package com.hirocode.hironews.data.source.remote

import android.util.Log
import com.hirocode.hironews.data.source.remote.network.ApiService
import com.hirocode.hironews.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsDataSource(private val apiService: ApiService) {
    fun getTopHeadlines(): Flow<List<ArticlesItem>> {
        return flow {
            try {
                val response = apiService.getTopHeadlines()
                val articles = response.articles
                if (articles.isNotEmpty()) {
                    emit(articles)
                } else {
                    throw Exception("Failed to get top headlines")
                }
            } catch (e: Exception) {
                Log.e("NewsDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}