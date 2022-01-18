package com.example.desafiobtg.data.domain.repository

import androidx.lifecycle.LiveData
import com.example.desafiobtg.data.dao.CurrencyDao
import com.example.desafiobtg.data.domain.model.CurrencyBo

class CurrencyBoRepository(private val currencyDao: CurrencyDao) {

    val getAllData: LiveData<List<CurrencyBo>> = currencyDao.getAllData()

    suspend fun insertData(currencyBo: CurrencyBo) {
        currencyDao.insertData(currencyBo)
    }

    suspend fun delete(currencyBo: CurrencyBo) {
        currencyDao.deleteItem(currencyBo)
    }
}