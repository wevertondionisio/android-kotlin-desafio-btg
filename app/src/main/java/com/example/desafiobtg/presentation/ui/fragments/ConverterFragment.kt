package com.example.desafiobtg.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.desafiobtg.R
import com.example.desafiobtg.data.domain.repository.CurrenciesRepository
import com.example.desafiobtg.presentation.CurrencyViewModel
import com.example.desafiobtg.presentation.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.fragment_converter.*

class ConverterFragment: Fragment(R.layout.fragment_converter){

    private lateinit var currencyViewModel: CurrencyViewModel

    var btnFromText : String = "Origem"
    var btnToText : String = "Destino"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyViewModel = CurrencyViewModelFactory(CurrenciesRepository()).create(CurrencyViewModel::class.java)

        setupView()

        // Begin here -> transferencia de dados entre fragments, atraves dos buttons "btnFrom" e "btnTo".
            setFragmentResultListener("requestKey") { requestKey: String, bundle: Bundle ->
                val btnOrigin = bundle.getString("btnOrigin")
                    if (btnOrigin.equals("btnFrom")) {
                            btnFromText = bundle.getString("coin").toString()
                    } else {
                            btnToText = bundle.getString("coin").toString()
                    }
                btnFrom.text = btnFromText
                btnTo.text = btnToText
                setupView()
            }
        // Finish here ...

        // Begin here -> Ao clicar no button "btnConvert" iniciará a conversão da moeda escolhida
        btnConvert.setOnClickListener {
            if(textViewConveter.text.isNullOrEmpty()) {
                textViewConveter.error = "É necessário inserir um valor."
            } else {
                val valor = textViewConveter.text.toString().toDouble()
                val origemDolar = currencyViewModel.quotesBo.cotacao.get("USD${btnFrom.text}")
                val destinoDolar = currencyViewModel.quotesBo.cotacao.get("USD${btnTo.text}")
                val origemConvertido = origemDolar?.times(valor)
                val resultado = origemConvertido?.times(destinoDolar!!)
                textViewResult.setText(resultado.toString())
            }
        }
        // Finish here ...

    }

    private fun setupView() {
        // Begin here -> "btnFrom" e "btnTo" ao selecionar a sigla aparecerá no button de acesso.
            btnFrom.setOnClickListener {
                findNavController().navigate(ConverterFragmentDirections.actionConverterFragmentToCurrencyFragment("btnFrom"))
            }
            btnTo.setOnClickListener {
                findNavController().navigate(ConverterFragmentDirections.actionConverterFragmentToCurrencyFragment("btnTo"))
            }
        // Finish here ...
    }
}
