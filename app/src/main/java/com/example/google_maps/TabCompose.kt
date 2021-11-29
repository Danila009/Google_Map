package com.example.google_maps

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun TabFun() {

    val selec = remember { mutableStateOf(false)}
    Tab(selected = selec.value, onClick = {
        selec.value = true
    }) {
        
    }
}

@Composable
fun TabRowFun() {
    val tabIndex = remember { mutableStateOf(0) }
    val tabData = listOf(
        "MUSIC" to Icons.Filled.Home,
        "MARKET" to Icons.Filled.ShoppingCart,
        "FILMS" to Icons.Filled.AccountBox,
        "BOOKS" to Icons.Filled.Settings,
    )
    ScrollableTabRow(selectedTabIndex = tabIndex.value) {
        tabData.forEachIndexed { index, pair ->
        Tab(selected = tabIndex.value == index, onClick = {
            tabIndex.value = index
        }, text = {
            Text(text = pair.first)
        }, icon = {
            Icon(imageVector = pair.second, contentDescription = null)
        },selectedContentColor = Color.Blue,unselectedContentColor = Color.Red)
    }
    }
}