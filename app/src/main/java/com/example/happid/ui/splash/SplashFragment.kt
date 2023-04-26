package com.example.happid.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.happid.R
import com.example.happid.utils.SharedPrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use coroutines to add a delay of 1 second before navigating to the next fragment
        viewLifecycleOwner.lifecycleScope.launch {
            // Switch to the IO Dispatcher to perform the delay
            withContext(Dispatchers.IO) {
                delay(3000) // Delay for 1 second
            }
            // Switch back to the Main Dispatcher to navigate to the next fragment
            if (SharedPrefManager.isSplashCompleted(view.context)) {
                navigateToLoginOrHome(view.context)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_splashSecondFragment)
            }
        }
    }

    private fun navigateToLoginOrHome(context: Context) {
        if (SharedPrefManager.isLoginCompleted(context)) {
            findNavController().navigate(R.id.action_splashFragment_to_profileFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}
