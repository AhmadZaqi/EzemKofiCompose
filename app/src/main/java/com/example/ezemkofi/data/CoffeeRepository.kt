package com.example.ezemkofi.data

import com.example.ezemkofi.model.Cart
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.model.CoffeeCategory
import com.example.ezemkofi.model.FakeCoffeeCategoryDataSource
import com.example.ezemkofi.model.FakeCoffeeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class CoffeeRepository {

    private val coffee = MutableStateFlow<List<Coffee>>(emptyList())
    private val coffeeCategory = MutableStateFlow<List<CoffeeCategory>>(emptyList())
    private val cart = MutableStateFlow<List<Cart>>(emptyList())

    init {
        if (coffee.value.isEmpty()) {
            coffee.value = FakeCoffeeDataSource.coffeeList
        }
        if (coffeeCategory.value.isEmpty()) {
            coffeeCategory.value = FakeCoffeeCategoryDataSource.coffeeCategoryList
        }
    }

    fun getCoffeeById(coffeeId: Int): Coffee? =
        coffee.value.find { it.id == coffeeId }

    fun getCoffeeByCategory(categoryId: Int): Flow<List<Coffee>> =
        coffee.map { coffees ->
            coffees.filter { it.category.id == categoryId }
        }

    fun getTopPicksCoffee(): Flow<List<Coffee>> =
        coffee.map { coffees ->
            coffees.sortedByDescending { it.rating }.take(5)
        }

    fun searchCoffee(query: String): Flow<List<Coffee>> =
        coffee.map { coffees ->
            if (query.isNotEmpty()) {
                coffees.filter { it.name.contains(query, ignoreCase = true) }
            } else {
                coffees
            }
        }

    fun getCoffeeCategory(): StateFlow<List<CoffeeCategory>> = coffeeCategory.asStateFlow()

    fun getAllCoffeeInCart(): StateFlow<List<Cart>> = cart.asStateFlow()

    fun addCoffeeToCart(coffeeId: Int, qty: Int) {
        val coffeeToAdd = getCoffeeById(coffeeId) ?: return
        cart.update { currentCart ->
            val existingItem = currentCart.find { it.coffee.id == coffeeId }
            if (existingItem != null) {
                currentCart.map { item ->
                    if (item.coffee.id == coffeeId) {
                        item.copy(count = item.count + qty)
                    } else {
                        item
                    }
                }
            } else {
                currentCart + Cart(coffeeToAdd, qty)
            }
        }
    }

    fun updateCoffeeInCart(coffeeId: Int, qty: Int) {
        cart.update { currentCart ->
            currentCart.map { item ->
                if (item.coffee.id == coffeeId) {
                    item.copy(count = qty)
                } else {
                    item
                }
            }.filter { it.count > 0 }
        }
    }

    companion object {
        @Volatile
        private var instance: CoffeeRepository? = null

        fun getInstance(): CoffeeRepository =
            instance ?: synchronized(this) {
                instance ?: CoffeeRepository().also { instance = it }
            }
    }
}