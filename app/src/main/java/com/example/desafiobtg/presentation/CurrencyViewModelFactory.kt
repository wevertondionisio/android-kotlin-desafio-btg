package com.example.desafiobtg.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafiobtg.data.domain.repository.CurrenciesRepository

class CurrencyViewModelFactory(private val repository: CurrenciesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(repository) as T
    }

}