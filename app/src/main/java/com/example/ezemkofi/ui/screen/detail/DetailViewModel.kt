package com.example.ezemkofi.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.data.CoffeeRepository
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CoffeeRepository
): ViewModel() {

    private val _uiStateCoffeeDetail: MutableStateFlow<UiState<Coffee>> = MutableStateFlow(UiState.Loading)

    val uiStateCoffeeDetail: StateFlow<UiState<Coffee>> get() = _uiStateCoffeeDetail

    fun getCoffeeDetail(coffeeId: Int){
        viewModelScope.launch {
            _uiStateCoffeeDetail.value = UiState.Success(repository.getCoffeeById(coffeeId)!!)
        }
    }

    fun addCoffeeToCart(coffeeId: Int, qty: Int){
        viewModelScope.launch {
            repository.addCoffeeToCart(coffeeId, qty)
        }
    }
}