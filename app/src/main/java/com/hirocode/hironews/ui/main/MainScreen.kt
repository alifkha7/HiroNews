package com.hirocode.hironews.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hirocode.hironews.R
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.ui.common.NewsState
import com.hirocode.hironews.ui.components.EverythingItem
import com.hirocode.hironews.ui.components.MainSection
import com.hirocode.hironews.ui.components.TopHeadlinesItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
) {
    val newsState by viewModel.newsState.collectAsState()
    val pagingArticle = viewModel.newsPaging.collectAsLazyPagingItems()

    Column(
        modifier = modifier
    ) {
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
            is NewsState.Error -> {
                val errorMessage = (newsState as NewsState.Error).message
                ErrorScreen(errorMessage)
            }
            is NewsState.Success -> {
                val articles = (newsState as NewsState.Success).data
                MainSection(
                    title = stringResource(id = R.string.breaking_news),
                    content = { TopHeadlinesList(articles) }
                )
                MainSection(
                    title = stringResource(id = R.string.all_news),
                    content = { EverythingList(pagingArticle) }
                )
            }
        }

    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
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

@Composable
fun EverythingList(pagingArticle: LazyPagingItems<Article>) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pagingArticle) { article ->
            if (article != null) {
                EverythingItem(article)
            }
        }
        if (pagingArticle.loadState.append is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        if (pagingArticle.loadState.append is LoadState.Error) {
            item { Text("Error loading more data!") }
        }
    }
}