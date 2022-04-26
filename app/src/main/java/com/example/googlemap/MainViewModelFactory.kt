@file:Suppress("UNCHECKED_CAST")

package com.example.googlemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlemap.data.Api
import com.example.googlemap.data.ApiOpenRoute

class MainViewModelFactory(
    private val api: Api,
    private val apiOpenRoute: ApiOpenRoute
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api = api, apiOpenRoute = apiOpenRoute) as T
    }
} 