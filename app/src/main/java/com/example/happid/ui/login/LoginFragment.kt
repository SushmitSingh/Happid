package com.example.happid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.happid.R
import com.example.happid.databinding.FragmentLoginBinding
import com.example.happid.utils.SharedPrefManager

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnRequestOtp.setOnClickListener {
            // Get the entered mobile number
            val mobileNumber = binding.inputNumber.text.toString().trim()

            // Generate OTP using ViewModel
            viewModel.generateOtp(mobileNumber)

            // Show dialog with generated OTP
            viewModel.otp.observe(viewLifecycleOwner) { otp ->
                // Save OTP to SharedPreferences
                SharedPrefManager.saveOtp(requireContext(), otp)
                showOtpDialog(otp, mobileNumber)
            }

        }
        return binding.root
    }

    private fun showOtpDialog(otp: String, mobileNumber: String) {
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.otp_dialog, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        builder.setCancelable(true) // Allow the dialog to be cancellable

        val dialog = builder.create()

        // Get reference to the UI components in the custom layout
        val etOtp = dialogView.findViewById<AppCompatTextView>(R.id.txtOtp)
        // Perform OTP verification logic here using etOtp.text.toString()
        etOtp.text = otp

        // Dismiss the dialog
        dialog.dismiss()

        // Navigate to OtpFragment with the generated OTP and mobile number as arguments
        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(otp, mobileNumber)
        findNavController().navigate(action)

        dialog.show()

    }
}
