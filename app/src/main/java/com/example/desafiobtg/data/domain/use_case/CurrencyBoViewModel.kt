package com.example.desafiobtg.data.domain.use_case

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiobtg.data.database.CurrencyDatabase
import com.example.desafiobtg.data.domain.model.CurrencyBo
import com.example.desafiobtg.data.domain.repository.CurrencyBoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyBoViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyDao = CurrencyDatabase.getDatabase(application).currencyDao()
    private val repository: CurrencyBoRepository = CurrencyBoRepository(currencyDao)

    fun insertData(currencyBo: CurrencyBo) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertData(currencyBo)
        }
    }

    fun deleteItem(currencyBo: CurrencyBo) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.delete(currencyBo)
        }
    }

}