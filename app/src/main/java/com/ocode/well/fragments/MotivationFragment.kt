package com.ocode.well.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ocode.well.R
import com.ocode.well.databinding.FragmentMotivationBinding
import com.ocode.well.viewmodel.QuoteViewModel

class MotivationFragment : Fragment() {

    private var _binding: FragmentMotivationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMotivationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getRandomQuote()

        binding.btnBackNav.setOnClickListener {
            findNavController().navigate(R.id.action_motivationFragment2_to_navigationFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}