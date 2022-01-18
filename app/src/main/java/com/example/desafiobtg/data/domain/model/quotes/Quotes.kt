package com.example.desafiobtg.data.domain.model.quotes

import java.io.Serializable

data class Quotes(
    val success: Boolean,
    val source: String,
    val quotes: Map<String, Double>
) : Serializable