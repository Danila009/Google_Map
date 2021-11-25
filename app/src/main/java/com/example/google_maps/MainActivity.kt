package com.example.google_maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
        setContent {
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                        ComposeButton()
                        XMLButton()
                    }
                }
            }
        }

   }
}
@Composable
fun XMLButton() {
    val context = LocalContext.current
    val intent = android.content.Intent(context, XMLMaps::class.java)
    Button(onClick = {

        context.startActivity(intent)
    },modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)) {
        Text(text = "XMLButton")
    }

}

@Composable
fun ComposeButton() {
    val context = LocalContext.current

    val intent = android.content.Intent(context, ComposeMaps::class.java)
    Button(onClick = {

        context.startActivity(intent)
    },modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp)) {
        Text(text = "ComposeButton")
    }
}