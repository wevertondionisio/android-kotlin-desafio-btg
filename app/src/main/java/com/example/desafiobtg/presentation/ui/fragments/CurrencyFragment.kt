package com.example.desafiobtg.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiobtg.R
import com.example.desafiobtg.adapter.CurrenciesAdapter
import com.example.desafiobtg.data.domain.model.CurrencyBo
import com.example.desafiobtg.data.domain.repository.CurrenciesRepository
import com.example.desafiobtg.presentation.CurrencyViewModel
import com.example.desafiobtg.presentation.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.android.synthetic.main.fragment_converter.view.*
import kotlinx.android.synthetic.main.fragment_list_currency.*
import java.util.*

class CurrencyFragment : Fragment(R.layout.fragment_list_currency),
    CurrenciesAdapter.OnItemClickListener {

    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var currencyBo : List<CurrencyBo>
    private lateinit var myAdapter: CurrenciesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CurrenciesRepository()
        val viewModelFactory = CurrencyViewModelFactory(repository)
        currencyViewModel = ViewModelProvider(this, viewModelFactory).get(CurrencyViewModel::class.java)
        currencyViewModel.currenciesResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val currencies: MutableList<CurrencyBo> = mutableListOf()
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                response.body()?.let {

                    for (currency in it.currencies){
                        val tempCurrency = CurrencyBo(
                            coin = currency.key,
                            name = currency.value,
                            id = 0
                        )
                        currencies.add(tempCurrency)
                    }
                }
                    currencyBo = currencies
                    with(rvListCurrencies){
                        layoutManager = LinearLayoutManager(activity)
                        myAdapter = CurrenciesAdapter(currencyBo, this@CurrencyFragment)
                        adapter = myAdapter
                    }
                } else {
                    Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
                }
            })
        }

    override fun onItemClick(currencyBo: CurrencyBo) {
        val coin = currencyBo.coin

        val bundle = Bundle()
        bundle.putString("btnOrigin", arguments?.getString("btnOrigin"))
        bundle.putSerializable("coin", coin)
        setFragmentResult("requestKey", bundle)

        Toast.makeText(activity,"${currencyBo.coin} ${currencyBo.name}", Toast.LENGTH_SHORT).show()
        findNavController().previousBackStackEntry?.savedStateHandle?.set("Callback", bundle)
        findNavController().popBackStack()
    }
}
