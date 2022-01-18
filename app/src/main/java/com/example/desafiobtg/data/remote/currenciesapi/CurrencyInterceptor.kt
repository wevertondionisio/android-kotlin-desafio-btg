package com.example.desafiobtg.data.remote.currenciesapi

import okhttp3.Interceptor
import okhttp3.Response

class CurrencyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .build()
        return chain.proceed(request)
    }
}