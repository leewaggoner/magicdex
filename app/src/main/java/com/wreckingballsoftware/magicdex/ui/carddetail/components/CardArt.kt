package com.wreckingballsoftware.magicdex.ui.carddetail.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.domain.models.MagicCardArtData
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import com.wreckingballsoftware.magicdex.ui.theme.magicTypography

@Composable
fun CardArt(
    modifier: Modifier = Modifier,
    artData: MagicCardArtData
) {
    Column(
        modifier = modifier.then(
            Modifier
                .padding(MaterialTheme.dimensions.padding)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        )
    ) {
        Text(
            text = artData.name,
            style = MaterialTheme.magicTypography.titleMedium
        )
        AsyncImage(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.padding)
                .align(Alignment.CenterHorizontally)
                .height(MaterialTheme.dimensions.cardArtHeight),
            model = artData.imageUrl,
            contentDescription = artData.name,
            placeholder = painterResource(id = R.drawable.card_back),
            error = painterResource(id = R.drawable.card_back),
            onError = { error ->
                Log.e("CardDetailScreen", "error: $error")
            }
        )
        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimensions.paddingSmall,
                ),
            text = stringResource(id = R.string.artist, artData.artist),
            style = MaterialTheme.magicTypography.label,
        )
    }
}

@Preview(name = "CardArt")
@Composable
fun CardArtPreview() {
    CardArt(
        artData = MagicCardArtData(
            name = "Archangel Avacyn",
            imageUrl = "",
            artist = "DaVinci"
        )
    )
}
