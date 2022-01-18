package com.example.desafiobtg.data.remote.currenciesapi

import com.example.desafiobtg.common.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrenciesRetrofit {

        private val client = OkHttpClient.Builder().apply {
            addInterceptor(CurrencyInterceptor())
        }.build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(CurreciesApi::class.java)
        }

}