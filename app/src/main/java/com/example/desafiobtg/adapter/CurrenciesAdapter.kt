package com.example.desafiobtg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiobtg.R
import com.example.desafiobtg.data.domain.model.CurrencyBo
import kotlinx.android.synthetic.main.item_currency_card.view.*

class CurrenciesAdapter(private val myList: List<CurrencyBo>,
                        private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>(){

    inner class CurrenciesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.tvName
        val tresLetras: TextView = itemView.tvActive
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency_card, parent, false)
        return CurrenciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val currency = myList[position]
        holder.nome.text = currency.name
        holder.tresLetras.text = currency.coin

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(currency)
        }
    }
    override fun getItemCount() = myList.size

    interface OnItemClickListener {
        fun onItemClick (currencyBo: CurrencyBo)
    }
}





