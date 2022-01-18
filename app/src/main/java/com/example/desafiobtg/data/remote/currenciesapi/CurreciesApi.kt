package com.example.desafiobtg.data.remote.currenciesapi

import com.example.desafiobtg.data.domain.model.currencies.Currencies
import com.example.desafiobtg.data.domain.model.quotes.Quotes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurreciesApi {

    @GET("list")
    suspend fun getCurrecies() : Response<Currencies>

    @GET("live")
    suspend fun getQuotes() : Quotes

    @GET("list")
    fun searchCurrencies(
        @Query("searchQuery") searchQuery: String
    ) : Response<List<Currencies>>

}

