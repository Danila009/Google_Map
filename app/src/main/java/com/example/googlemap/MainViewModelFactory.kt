@file:Suppress("UNCHECKED_CAST")

package com.example.googlemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlemap.data.Api

class MainViewModelFactory(
    private val api: Api
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api = api) as T
    }
}