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

@Composable
fun FilterPill(
    modifier: Modifier = Modifier,
    state: FilterPillState,
    category: String,
    onFilterClick: (FilterCategory) -> Unit
) {

    val stateModifier: Modifier = Modifier
        .then(if (state.selected) {
            Modifier
                .background(shape = RoundedCornerShape(100.dp), color = Color.White)
                .padding(8.dp)

        } else {
            Modifier
                .background(color = Color.Transparent)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(8.dp)
                .clickable { onFilterClick(FilterCategory.valueOf(category)) }
        })


    val textColor = if (state.selected) Color.Black else Color.White
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