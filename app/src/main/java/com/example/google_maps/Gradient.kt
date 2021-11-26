package com.example.google_maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.google_maps.ui.theme.Google_MapsTheme
import com.example.google_maps.ui.theme.gradientColor1
import com.example.google_maps.ui.theme.gradientColor2

class Gradient : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxHeight()){
                            Google_MapsTheme {
                                GradientFun("Button", Color.White,Brush.horizontalGradient(colors = listOf(
                                    gradientColor1,
                                    gradientColor2
                                ))){
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientFun(
    text:String,
    textColor: Color,
    gradient: Brush,
    onClick:()->Unit
) {
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),contentPadding = PaddingValues()) {
        Box(modifier = Modifier
            .background(gradient)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),contentAlignment = Alignment.Center){
            Text(text = text,color=textColor)
        }
    }
}
