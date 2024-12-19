package com.example.ezemkofi.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.data.CoffeeRepository
import com.example.ezemkofi.model.Cart
import com.example.ezemkofi.ui.common.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {
    val uiStateCart: StateFlow<UiState<List<Cart>>> = repository.getAllCoffeeInCart()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )

    fun updateCoffeeInCart(coffeeId: Int, qty: Int) {
        viewModelScope.launch {
            repository.updateCoffeeInCart(coffeeId, qty)
        }
    }

    fun orderAllCoffeeInCart(){
        viewModelScope.launch {
            val listCart = repository.getAllCoffeeInCart().value
            listCart.forEach { item ->
                repository.updateCoffeeInCart(item.coffee.id, 0)
            }
        }
    }
}