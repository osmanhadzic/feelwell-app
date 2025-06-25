package com.ocode.well.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ocode.well.R
import com.ocode.well.databinding.FragmentHaikuBinding
import com.ocode.well.viewmodel.HaikuViewModel


class HaikuFragment : Fragment() {

    private var _binding: FragmentHaikuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HaikuViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHaikuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.btnBackNav.setOnClickListener {
            findNavController().navigate(R.id.action_haikuFragment_to_navigationFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HaikuFragment().apply {

            }
    }
}
