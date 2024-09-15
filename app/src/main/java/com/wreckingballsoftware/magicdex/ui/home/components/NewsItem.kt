package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.News
import com.wreckingballsoftware.magicdex.ui.theme.CardWhite
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun NewsItem(
    news: News,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = MaterialTheme.dimensions.spaceSmall),
    ) {
        Card(
            modifier = Modifier
                .height(MaterialTheme.dimensions.newsCardHeight)
                .fillMaxWidth()
                .clickable { onClick(news.link) },
            colors = CardDefaults.cardColors(
                containerColor = CardWhite
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.dimensions.spaceSmall)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(MaterialTheme.dimensions.paddingVerySmall)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = MaterialTheme.dimensions.paddingVerySmall),
                        text = news.title,
                        style = MaterialTheme.magicTypography.newsTitle,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(text = news.date, style = MaterialTheme.magicTypography.newsDate)
                }
                AsyncImage(
                    modifier = Modifier
                        .weight(0.4f)
                        .clip(RoundedCornerShape(MaterialTheme.dimensions.newsImageCorner)),
                    model = news.imageUrl,
                    placeholder = painterResource(id = R.drawable.news_placeholder),
                    fallback = painterResource(id = R.drawable.news_placeholder),
                    error = painterResource(id = R.drawable.news_placeholder),
                    contentDescription = stringResource(id = R.string.magic_image),
                )
            }
        }
    }
}

@Preview(name = "NewsItem")
@Composable
fun NewsItemPreview() {
    NewsItem(
        News(
            title = "This is a very, very, very, extremely very long news item title",
            date = "2024-01-01",
        ),
        onClick = { },
    )
}