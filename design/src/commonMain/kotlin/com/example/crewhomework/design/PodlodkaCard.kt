package com.example.crewhomework.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PodlodkaCard(
    modifier: Modifier = Modifier,
    text: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow,
        ),
        modifier = modifier
            .size(180.dp, 120.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                color = Color(105, 32, 240),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}