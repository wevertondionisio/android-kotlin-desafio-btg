package com.example.desafiobtg.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.desafiobtg.data.domain.model.CurrencyBo

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table")
    fun getAllData() : LiveData<List<CurrencyBo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(currencyBo: CurrencyBo)

    @Delete
    suspend fun deleteItem(currencyBo: CurrencyBo)

}