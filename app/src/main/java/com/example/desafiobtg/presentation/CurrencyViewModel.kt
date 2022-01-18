package com.example.desafiobtg.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiobtg.common.Resource
import com.example.desafiobtg.data.domain.model.QuotesBo
import com.example.desafiobtg.data.domain.model.currencies.Currencies
import com.example.desafiobtg.data.domain.repository.CurrenciesRepository
import kotlinx.android.synthetic.main.fragment_list_currency.*
import kotlinx.coroutines.launch
import retrofit2.Response

class CurrencyViewModel(private val repository: CurrenciesRepository) : ViewModel() {

    val currenciesResponse: MutableLiveData<Response<Currencies>> = MutableLiveData()
    lateinit var quotesBo: QuotesBo

    init {
       getCurrencies()
       getQuotes()
    }

    fun getCurrencies() {
        viewModelScope.launch {
            val response = repository.getCurriencies()
            currenciesResponse.value = response
        }
    }

    fun getQuotes() {
        viewModelScope.launch {
            val response = repository.getQuotes()
            when (response) {
                is Resource.Success -> {
                    val quoteBo = QuotesBo(response.data?.quotes!!)
                    this@CurrencyViewModel.quotesBo = quoteBo
                }
                is Resource.Error -> {
                    Log.d("TAG", "getQuotes: ")
                }
            }
        }
    }


}
