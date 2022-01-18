package com.example.desafiobtg.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "currency_table")
data class CurrencyBo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coin: String,
    val name: String,
 ) : Serializable