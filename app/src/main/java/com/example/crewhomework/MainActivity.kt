@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.crewhomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.crewhomework.ui.theme.CrewHomeWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrewHomeWorkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CrewHomeWorkScreen()
                }
            }
        }
    }
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

@Composable
fun PodlodkaCard(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow,
        ),
        modifier = modifier
            .size(180.dp, 120.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.crew_logo),
                colorFilter = ColorFilter.tint(Color(105, 32, 240)),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

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

@Composable
fun FlippableCard(
    modifier: Modifier = Modifier,
    colors: CardColors,
    isFlipped: Boolean,
    backContent: @Composable () -> Unit = {},
    faceContent: @Composable () -> Unit,
) {
    val flipAnimationAngle by animateFloatAsState(
        targetValue = if (isFlipped) 0f else 180f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ),
        label = "flip"
    )

    Card(
        colors = colors,
        modifier = modifier
            .graphicsLayer {
                rotationX = flipAnimationAngle
                cameraDistance = 16f
            }
    ) {
        if (flipAnimationAngle > 90) {
            backContent()
        } else {
            faceContent()
        }
    }
}