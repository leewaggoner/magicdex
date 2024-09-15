package com.wreckingballsoftware.magicdex.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.home.components.models.News
import com.wreckingballsoftware.magicdex.ui.home.components.models.NewsList
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun NewsSection(
    modifier: Modifier = Modifier,
    newsList: NewsList,
    onViewAllClick: () -> Unit,
    onViewItemClick: (String) -> Unit,
) {
    Column(
        modifier = modifier.then(Modifier
            .fillMaxWidth(),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.dimensions.padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.magic_news),
                style = MaterialTheme.magicTypography.subtitle,
            )
            Text(
                modifier = Modifier
                    .clickable { onViewAllClick() },
                text = stringResource(id = R.string.view_all),
                style = MaterialTheme.magicTypography.hyperlink,
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            items(newsList.size) { index ->
                NewsItem(
                    news = newsList[index],
                    onClick = { link -> onViewItemClick(link) },
                )
            }
        }
    }
}

@Preview(name = "News Section")
@Composable
fun NewsSectionPreview() {
    NewsSection(
        newsList = listOf(
            News(title = "Title 1", date = "10/15/2024"),
            News(title = "Title 2", date = "10/14/2024"),
            News(title = "Title 3", date = "10/13/2024"),
            News(title = "Title 4", date = "10/12/2024"),
            News(title = "Title 5", date = "10/11/2024"),
            News(title = "Title 6", date = "10/10/2024"),
            News(title = "Title 7", date = "10/09/2024"),
            News(title = "Title 8", date = "10/08/2024"),
            News(title = "Title 9", date = "10/07/2024"),
            News(title = "Title 10", date = "10/06/2024"),
        ),
        onViewAllClick = { },
        onViewItemClick = { },
    )
}