package com.example.google_maps

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.google_maps.ui.theme.Google_MapsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentMaps = Intent(this, ComposeMaps::class.java)
        val intentRecuclerView = Intent(this, RecuclerView::class.java)
        val intentGradient = Intent(this, Gradient::class.java)
        val intentSearch = Intent(this, SearchView::class.java)
        val intentButtonNavigationView = Intent(this, ButtonNavigationView::class.java)

        setContent {
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn(){
                        item {
                            ComposeButton("GoogleMap",intentMaps)
                            ComposeButton(text = "RecuclerView", intent = intentRecuclerView)
                            ComposeButton(text = "Gradient", intent = intentGradient)
                            ComposeButton(text = "SearchView", intent = intentSearch)
                            ComposeButton(text = "ButtonNavigationView", intent = intentButtonNavigationView)
                            ComposeButton(text = "NavigationView", intent = intentMaps)
                        }
                    }
                }
            }
        }
   }
}

@Composable
fun ComposeButton(text:String,intent: Intent) {
    val context = LocalContext.current

    Button(onClick = {

        context.startActivity(intent)
    },modifier = Modifier
        .fillMaxWidth()
        .padding(top = 13.dp)) {
        Text(text = text)
    }
}