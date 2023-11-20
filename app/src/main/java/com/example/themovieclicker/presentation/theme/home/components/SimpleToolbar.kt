package com.example.themovieclicker.presentation.theme.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themovieclicker.R
import com.example.themovieclicker.presentation.theme.ui.ClickOhBackgruond
import com.example.themovieclicker.presentation.theme.ui.ClickOhOnBackground


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun SimpleToolbar() {
    TopAppBar(contentColor = ClickOhOnBackground, backgroundColor = ClickOhBackgruond) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            //Spacer(modifier = Modifier.size(15.dp))
            AppImageLogo(modifier = Modifier.align(Alignment.Center))
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { },
                imageVector = ImageVector.vectorResource(id = R.drawable.filter_ic),
                contentDescription = stringResource(
                    R.string.filter_movies_icon_desc
                ),
                tint = ClickOhOnBackground,
            )
        }

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = ClickOhBackgruond)
        ) {
            androidx.compose.material3.FilterChip(selected = false, label = {
                Text(text = "Favorites")
            }, onClick = { })
        }
    }

}