package com.ocode.well.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ocode.well.R
import com.ocode.well.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {

    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.goToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_motivationFragment)
        }

        binding.goToToHaiku.setOnClickListener {
            findNavController().navigate(R.id.action_navigationFragment_to_haikuFragment)
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
            NavigationFragment().apply {
                arguments = Bundle().apply {
                    // Add parameters if needed
                }
            }
    }
}
