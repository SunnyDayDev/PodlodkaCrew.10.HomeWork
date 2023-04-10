@file:OptIn(ExperimentalMaterialApi::class)

package com.example.crewhomework

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.crewhomework.design.LikeCard
import com.example.crewhomework.design.PodlodkaCard

@Composable
fun App() {
    CrewHomeWorkScreen()
}


@Composable
fun CrewHomeWorkScreen() {
    val state = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    BackdropScaffold(
        scaffoldState = state,
        appBar = { },
        peekHeight = 56.dp,
        backLayerBackgroundColor = Color(105, 32, 240),
        gesturesEnabled = true,
        backLayerContent = { CrewBackdropContent(state) },
        stickyFrontLayer = false,
        headerHeight = 92.dp,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp, 4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.Gray)
                        .align(Alignment.CenterHorizontally)
                )
            }
        },
    )
}

@Composable
fun CrewBackdropContent(state: BackdropScaffoldState) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val peekHeightInPx = LocalDensity.current.run { 56.dp.toPx() }
        val maxPeekOffset = LocalDensity.current.run { maxHeight.toPx() - peekHeightInPx }
        val peekOffset by remember {
            derivedStateOf { state.offset.value - peekHeightInPx }
        }
        val peekRatio by remember {
            derivedStateOf { peekOffset / maxPeekOffset }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            PodlodkaCard(
                text = "Podlodka\nfrom ${getPlatform().name}",
                modifier = Modifier
                    .graphicsLayer {
                        translationY =
                            -(maxPeekOffset - peekOffset) / 2 + (size.height / 2) * (1 - peekRatio)
                        val scale = 0.8f * peekRatio + 0.2f
                        scaleY = scale
                        scaleX = scale
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))

            var canBeFlipped by remember { mutableStateOf(false) }
            if (
                state.progress.from == BackdropValue.Revealed &&
                state.progress.to == BackdropValue.Concealed &&
                state.progress.fraction > 0.3f) {
                canBeFlipped = true
            }

            LikeCard(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = peekRatio * 2 - 1
                    },
                isFlipped = canBeFlipped &&
                        state.progress.fraction == 1f &&
                        state.progress.to == BackdropValue.Revealed,
            )
        }
    }
}