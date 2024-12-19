package com.example.ezemkofi.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.data.CoffeeRepository
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {

    private val _uiStateCoffee: MutableStateFlow<UiState<List<Coffee>>> = MutableStateFlow(UiState.Loading)

    val uiStateCoffee: StateFlow<UiState<List<Coffee>>> get() = _uiStateCoffee

    fun searchCoffee(query: String){
        viewModelScope.launch {
            repository.searchCoffee(query)
                .catch {
                    _uiStateCoffee.value = UiState.Error(it.message.toString())
                }
                .collect{ coffee ->
                    _uiStateCoffee.value = UiState.Success(coffee)
                }
        }
    }
}