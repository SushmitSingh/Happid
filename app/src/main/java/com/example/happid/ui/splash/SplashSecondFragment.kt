package com.example.happid.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.happid.R
import com.example.happid.databinding.FragmentSplashSecondBinding
import com.example.happid.utils.SharedPrefManager

class SplashSecondFragment : Fragment() {

    private lateinit var binding: FragmentSplashSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_splashSecondFragment_to_loginFragment)
            SharedPrefManager.saveSplashCompletion(view.context, true)
        }
    }
}