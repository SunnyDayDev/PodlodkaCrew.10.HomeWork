package com.example.crewhomework.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LikeCard(
    modifier: Modifier = Modifier,
    isFlipped: Boolean,
) {
    FlippableCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        ),
        modifier = modifier
            .size(180.dp, 120.dp),
        isFlipped = isFlipped,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center),
            )
        }
    }
}