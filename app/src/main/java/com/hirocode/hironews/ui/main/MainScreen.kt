package com.hirocode.hironews.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hirocode.hironews.R
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.ui.common.NewsState
import com.hirocode.hironews.ui.components.MainSection
import com.hirocode.hironews.ui.components.TopHeadlinesItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
) {
    viewModel.newsState.collectAsState().value.let { newsState ->
        when (newsState) {
            NewsState.Loading -> {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                viewModel.getTopHeadlines()
            }
            is NewsState.Error -> {}
            is NewsState.Success -> {
                MainSection(
                    title = stringResource(id = R.string.breaking_news),
                    content = { TopHeadlinesList(newsState.data) }
                )
            }
        }
    }
}

@Composable
fun TopHeadlinesList(
    listArticle: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listArticle) { article ->
            TopHeadlinesItem(article)
        }
    }
}