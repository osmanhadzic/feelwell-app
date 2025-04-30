package com.example.well.viewmodel

import androidx.lifecycle.*
import com.example.well.data.QuoteRepository
import com.example.well.model.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val repository = QuoteRepository()

    private val _quote = MutableLiveData<Quote?>()
    val quote: LiveData<Quote?> = _quote

    init {
        fetchQuote()
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            _quote.value = repository.getRandomQuote()
        }
    }
}