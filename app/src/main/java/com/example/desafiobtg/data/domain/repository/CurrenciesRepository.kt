package com.example.desafiobtg.data.domain.repository

import com.example.desafiobtg.common.Resource
import com.example.desafiobtg.data.remote.currenciesapi.CurrenciesRetrofit
import com.example.desafiobtg.data.domain.model.currencies.Currencies
import com.example.desafiobtg.data.domain.model.quotes.Quotes
import retrofit2.Response
import java.lang.Exception

class CurrenciesRepository {

    suspend fun getCurriencies() : Response<Currencies> {
      return CurrenciesRetrofit.api.getCurrecies()
    }

    suspend fun getQuotes() : Resource<Quotes> {
        val response = try {
            CurrenciesRetrofit.api.getQuotes()
        } catch (e: Exception) {
            return Resource.Error("aconteceu alguma coisa")
        }
        return Resource.Success(response)
    }

     fun searchCurrencies(searchQuery : String): Response<List<Currencies>> {
          return CurrenciesRetrofit.api.searchCurrencies(searchQuery)
     }

}