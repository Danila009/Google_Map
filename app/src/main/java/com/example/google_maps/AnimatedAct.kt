package com.example.google_maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.google_maps.ui.theme.Google_MapsTheme

@ExperimentalAnimationApi
class AnimatedAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                BikeScreen()
                Animated()
            }
        }
    }
}
@Preview
@ExperimentalAnimationApi
@Composable
fun Animated() {

    //val editable = remember { mutableStateOf(true)}
    //AnimatedVisibility(visible = editable.value) {
    //    Text(text = "Text")
    //}
    val visible = remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(
            initialOffsetY = { with(density) { -40.dp.roundToPx() } }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text("Hello",
            Modifier
                .fillMaxWidth()
                .height(200.dp))
    }
}
@Preview
@Composable
fun BikeScreen() {
    val bikeState = remember { mutableStateOf(BikePosition.Start) }
    val bigState = remember { mutableStateOf(BikePosition.Start) }

    val offsetAnimation: Dp by animateDpAsState(
        if (bikeState.value == BikePosition.Start) 5.dp else 300.dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )
    val offsetAnimationBigState: Dp by animateDpAsState(
        if (bigState.value == BikePosition.Start) 5.dp else 70.dp,
        tween(10000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    )  {
        Image(
            painter = painterResource(R.drawable.ic_baseline_directions_bike_24),
            contentDescription = null,
            modifier = Modifier
                .height(offsetAnimationBigState)
                .absoluteOffset(x = offsetAnimation)
                .width(offsetAnimationBigState)
        )
        Button(
            onClick = {
                bikeState.value = when (bikeState.value) {
                    BikePosition.Start -> BikePosition.Finish
                    BikePosition.Finish -> BikePosition.Start
                }
                bigState.value = when (bigState.value) {
                    BikePosition.Start -> BikePosition.Finish
                    BikePosition.Finish -> BikePosition.Start
                }
            }, modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Ride")
        }
    }
}
