package com.example.google_maps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.google_maps.ui.theme.CustomTheme
import com.example.google_maps.ui.theme.DarkColors
import com.example.google_maps.ui.theme.Google_MapsTheme
import com.example.google_maps.ui.theme.LightColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentMaps = Intent(this, ComposeMaps::class.java)
        val intentRecuclerView = Intent(this, RecuclerView::class.java)
        val intentGradient = Intent(this, Gradient::class.java)
        val intentSearch = Intent(this, SearchView::class.java)
        val intentButtonNavigationView = Intent(this, ButtonNavigationView::class.java)
        val intentAdmob = Intent(this, AdMobCompose::class.java)

        setContent {
            val them = remember { mutableStateOf(true)}



                CustomTheme(them.value){
                        Surface(color = colors.background) {

                            Scaffold(bottomBar = {
                                Row {
                                    when(them.value){
                                        true->{
                                            Text("Dark",Modifier.padding(all = 5.dp))
                                            Image(painter = painterResource(id = R.drawable.dark), contentDescription = null,Modifier.padding(all = 5.dp))
                                        }
                                        false->{
                                            Text("Light",Modifier.padding(all = 5.dp))
                                            Image(painter = painterResource(id = R.drawable.light), contentDescription = null,Modifier.padding(all = 5.dp))
                                        }
                                    }

                                    Column {
                                        Switch(checked = them.value, onCheckedChange ={
                                            them.value = it
                                        },Modifier.padding(all = 5.dp))
                                    }

                                }
                            }){
                                LazyColumn{
                                    item{
                                        Column {
                                            ComposeButton("GoogleMap",intentMaps)
                                            ComposeButton(text = "RecuclerView", intent = intentRecuclerView)
                                            ComposeButton(text = "Gradient", intent = intentGradient)
                                            ComposeButton(text = "SearchView", intent = intentSearch)
                                            ComposeButton(text = "ButtonNavigationView", intent = intentButtonNavigationView)
                                            ComposeButton(text = "NavigationView", intent = intentMaps)
                                            ComposeButton(text = "AdMob", intent = intentAdmob)
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