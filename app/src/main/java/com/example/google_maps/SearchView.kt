package com.example.google_maps

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import com.example.google_maps.customStuff.CustomBottomNavigation
import com.example.google_maps.customStuff.Screen
import com.example.google_maps.searchPackge.MainScreen
import com.example.google_maps.searchPackge.MainViewModel
import com.example.google_maps.ui.theme.Google_MapsTheme

class SearchView : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor=MaterialTheme.colors.background.toArgb()
            window.navigationBarColor=MaterialTheme.colors.background.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.navigationBarDividerColor=MaterialTheme.colors.onBackground.copy(alpha = 0.1f).toArgb()
            }

            val currentScreen= mutableStateOf<Screen>(Screen.Search)
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) {
                                currentScreen.value=it
                                Toast.makeText(this,it.id, Toast.LENGTH_SHORT).show()
                                when(it.id){
                                    "home"->{

                                    }
                                    "profile"->{

                                    }
                                    "settings"->{

                                    }
                                }
                            }
                        }
                    ) {
                        Surface(color = MaterialTheme.colors.background) {
                        MainScreen(mainViewModel = mainViewModel)
                    }
                    }
                }
            }
        }
    }
}

