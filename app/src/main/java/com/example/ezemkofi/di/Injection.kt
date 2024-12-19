package com.example.ezemkofi.di

import com.example.ezemkofi.data.CoffeeRepository

object Injection {
    fun provideRepository(): CoffeeRepository{
        return CoffeeRepository.getInstance()
    }
}