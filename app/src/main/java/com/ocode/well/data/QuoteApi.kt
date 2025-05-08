package com.ocode.well.data

import com.ocode.well.model.Quote
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("MotivationalAPI/en.json")
    suspend fun getQuotes(): List<Quote>
}
