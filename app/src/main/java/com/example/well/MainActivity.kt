package com.ocode.well

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ocode.well.databinding.ActivityMainBinding
import com.ocode.well.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.quote.observe(this, Observer { quote ->
            if (quote != null) {
                binding.tvPhrase.text = "\"${quote.phrase}\""
                binding.tvAuthor.text = "- ${quote.author}"
            } else {
                binding.tvPhrase.text = "Failed to load quote."
                binding.tvAuthor.text = ""
            }
        })
    }
}
