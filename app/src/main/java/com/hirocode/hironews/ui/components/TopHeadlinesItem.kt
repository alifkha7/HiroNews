package com.hirocode.hironews.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hirocode.hironews.R
import com.hirocode.hironews.domain.model.Article
import com.hirocode.hironews.utils.convertTo

@Composable
fun TopHeadlinesItem(
    article: Article,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .widthIn(max = 360.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                error = painterResource(R.drawable.broken_image),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = article.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = article.publishedAt.convertTo("dd MMM, yyyy"),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopHeadlinesItemPreview() {
    MaterialTheme {
        TopHeadlinesItem(
            article = Article(
                "Title",
                "https://www.msn.com/en-us/sports/cricket/article-test-title-article-test-title-article-test-title-article-test-title-article-test-title-article-test-title/ar-AA1815ho",
                "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA10JrdJ.img?w=768&h=432&m=6&x=246&y=140&s=0&d=0",
                "01 Mar, 2023"
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}