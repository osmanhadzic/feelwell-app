package com.ocode.well.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocode.well.data.QuoteRepository
import com.ocode.well.model.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val repository = QuoteRepository()
    private val _quote = MutableLiveData<Quote?>()

    private val _quoteText = MutableLiveData<String>("Loading...")
    val quoteText: LiveData<String> get() = _quoteText

    private val _quoteAuthor = MutableLiveData<String>("Unknown")
    val quoteAuthor: LiveData<String> get() = _quoteAuthor

    val quote: LiveData<Quote?> = _quote

    fun getRandomQuote() {
        viewModelScope.launch {
            val randomQuote = repository.getRandomQuote()
            _quote.value = randomQuote

            // Update quoteText and quoteAuthor based on the fetched quote
            _quoteText.value = randomQuote?.phrase ?: "No quote available"
            _quoteAuthor.value = randomQuote?.author ?: "Unknown"
        }
    }
}
