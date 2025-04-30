package com.example.well.data

import com.example.well.model.Quote
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("MotivationalAPI/en.json")
    suspend fun getQuotes(): List<Quote>
}