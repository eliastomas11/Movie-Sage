package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.domain.FilterCategory
import com.example.themovieclicker.presentation.theme.home.FilterBarState

@Composable
fun FilterPill(
    modifier: Modifier = Modifier,
    state: FilterBarState,
    category: String,
    onFilterClick: (FilterCategory) -> Unit
) {
    var textColor = Color.White
    var stateModifier = Modifier
        .background(color = Color.Transparent)
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(8.dp)
        .clickable { onFilterClick(FilterCategory.valueOf(category)) }
    if (category == state.filterSelected.name) {
        textColor = Color.Black
        stateModifier =
            Modifier
                .background(shape = RoundedCornerShape(100.dp), color = Color.White)
                .padding(8.dp)
    }

    Box(
        modifier = stateModifier
    ) {
        Text(
            text = category,
            color = textColor,
            textAlign = TextAlign.Center,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
        )
    }
}