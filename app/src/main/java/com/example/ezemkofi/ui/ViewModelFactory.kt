package com.example.ezemkofi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezemkofi.data.CoffeeRepository
import com.example.ezemkofi.ui.screen.cart.CartViewModel
import com.example.ezemkofi.ui.screen.detail.DetailViewModel
import com.example.ezemkofi.ui.screen.home.HomeViewModel
import com.example.ezemkofi.ui.screen.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: CoffeeRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}