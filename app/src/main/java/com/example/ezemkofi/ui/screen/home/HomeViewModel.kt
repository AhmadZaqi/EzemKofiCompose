package com.example.ezemkofi.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.data.CoffeeRepository
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.model.CoffeeCategory
import com.example.ezemkofi.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CoffeeRepository
): ViewModel() {

    private val _uiStateCoffee: MutableStateFlow<UiState<List<Coffee>>> =MutableStateFlow(UiState.Loading)
    private val _uiStateCategory: MutableStateFlow<UiState<List<CoffeeCategory>>> = MutableStateFlow(UiState.Loading)
    private val _uiStateTopPicks: MutableStateFlow<UiState<List<Coffee>>> = MutableStateFlow(UiState.Loading)

    val uiStateCoffee: StateFlow<UiState<List<Coffee>>> get() = _uiStateCoffee
    val uiStateCategory: StateFlow<UiState<List<CoffeeCategory>>> get() = _uiStateCategory
    val uiStateTopPicks: StateFlow<UiState<List<Coffee>>> get() = _uiStateTopPicks

    fun getCoffeeByCategory(coffeeId: Int){
        viewModelScope.launch {
            repository.getCoffeeByCategory(coffeeId)
                .catch {
                    _uiStateCoffee.value = UiState.Error(it.message.toString())
                }
                .collect{ coffee->
                    _uiStateCoffee.value  =UiState.Success(coffee)
                }
        }
    }

    fun getTopPicksCoffee(){
        viewModelScope.launch {
            repository.getTopPicksCoffee()
                .catch {
                    _uiStateTopPicks.value = UiState.Error(it.message.toString())
                }
                .collect{ coffee->
                    _uiStateTopPicks.value = UiState.Success(coffee)
                }
        }
    }

    fun getCoffeeCategory(){
        viewModelScope.launch {
            repository.getCoffeeCategory()
                .catch {
                    _uiStateCategory.value = UiState.Error(it.message.toString())
                }
                .collect{ coffeeCategory->
                    _uiStateCategory.value  = UiState.Success(coffeeCategory)
                }
        }
    }
}