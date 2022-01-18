package com.example.desafiobtg.data.domain.model.currencies

import java.io.Serializable

data class Currencies(
    val success: Boolean,
    val currencies: Map<String, String>
) : Serializable