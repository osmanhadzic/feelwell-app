package com.ocode.well.data

import com.ocode.well.model.Quote

class QuoteRepository {
    private val api = RetrofitService.api

    suspend fun getRandomQuote(): Quote? {
        return try {
            val quotes = api.getQuotes()
            quotes.random()
        } catch (e: Exception) {
            null
        }
    }
}
