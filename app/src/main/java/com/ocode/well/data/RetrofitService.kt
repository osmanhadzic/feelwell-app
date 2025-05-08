package com.ocode.well.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gomezmig03.github.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: QuoteApi = retrofit.create(QuoteApi::class.java)
}
