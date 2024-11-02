package com.wreckingballsoftware.magicdex.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.ui.theme.dimensions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val IMAGE_ID_BASE = "image"

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectOneTimeEvents(action: (T) -> Unit) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                collect { event ->
                    action(event)
                }
            }
        }
    }
}

/**
 * Create an AnnotatedString from a string that contains {X}, where X is an alpha-numeric character
 * that represents an image
 *
 * @return the AnnotatedString
 */
@Composable
fun String.withImagesToAnnotatedText() : AnnotatedString {
    val regex = Regex("\\{([a-zA-Z0-9])\\}")
    val matches = regex.findAll(input = this)
    var index = 0
    val annotatedString = buildAnnotatedString {
        var lastIndex = 0
        for (match in matches) {
            val start = match.range.first
            val end = match.range.last + 1
            append(this@withImagesToAnnotatedText.substring(lastIndex, start))
            appendInlineContent(id = IMAGE_ID_BASE + index, alternateText = match.value)
            index++
            lastIndex = end
        }
        append(this@withImagesToAnnotatedText.substring(lastIndex))
    }
    return annotatedString
}

/**
 * Create a map of InlineTextContent from a string that contains {X}, where X is an alpha-numeric
 * character that represents an image
 *
 * @return the map of InlineTextContent
 */
@Composable
fun String.inlineContent(): Map<String, InlineTextContent> {
    val regex = Regex("\\{([a-zA-Z0-9])\\}")
    val images = regex.findAll(this).map { it.value }.toList()
    val inlineContent = mutableMapOf<String, InlineTextContent>()
    images.forEachIndexed { index, image ->
        inlineContent[IMAGE_ID_BASE + index] = InlineTextContent(
            Placeholder(
                width = MaterialTheme.dimensions.magicCardManaTextSize,
                height = MaterialTheme.dimensions.magicCardManaTextSize,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            ),
            children = {
                val imageName = image.imageName()
                val manaId = imageName.mapManaToResource()
                Image(
                    painter = painterResource(id = manaId),
                    contentDescription = image
                )
            }
        )
    }
    return inlineContent
}

/**
 * Converts a string in the format "mana_x", where x is an alpha-numeric character, to an image
 * resource id
 *
 * @return the image resource id
 */
@Composable
fun String.mapManaToResource() : Int {
    val manaId: Int = if (LocalInspectionMode.current) {
        R.drawable.mana_w
    } else {
        val id = LocalContext.current.resources.getIdentifier(
            this,
            "drawable",
            LocalContext.current.packageName
        )
        if (id != 0) {
            id
        } else {
            R.drawable.mana_unexpected
        }
    }
    return manaId
}

/**
 * Converts a string in the format "{X}" (Where X is an alpha-numeric character),
 * to an image name, "mana_x"
 *
 * @return the image name
 */
fun String.imageName() : String {
    assert(this.isNotEmpty())
    val regEx = Regex("[^a-zA-Z0-9]")
    val mana = regEx.replace(this, "").lowercase()
    return "mana_$mana"
}