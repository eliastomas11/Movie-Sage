package com.example.themovieclicker.presentation.theme.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.R
import com.example.themovieclicker.domain.MovieModel
import com.example.themovieclicker.presentation.theme.home.components.starAmountCalculator
import com.example.themovieclicker.presentation.theme.ui.ClickOhAccent
import com.example.themovieclicker.presentation.theme.ui.ClickOhOnBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailInfoItem(
    modifier: Modifier = Modifier,
    movie: MovieModel,
    onClick: () -> Unit,
    favoriteState: Boolean
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = movie.title,
            color = ClickOhOnBackground,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { onClick() }) {
            if (favoriteState) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = ClickOhAccent,
                    contentDescription = stringResource(R.string.favorite_checked_content_desc)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    tint = ClickOhAccent,
                    contentDescription = stringResource(R.string.favorite_unchecked_content_desc)
                )
            }

        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        for (i in 1..movie.starAmountCalculator()) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = stringResource(R.string.star_content_desc),
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(32.dp))

    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Release date: ${movie.releaseDate}",
        color = ClickOhOnBackground,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(8.dp),
        maxLines = 1,
    )
    Text(
        text = movie.overview,
        color = ClickOhOnBackground,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(8.dp),
        overflow = TextOverflow.Ellipsis
    )
}