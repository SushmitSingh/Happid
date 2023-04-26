package com.example.happid.ui.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.happid.R
import com.example.happid.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding
    private lateinit var viewModel: OtpViewModel
    private val args: OtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using data binding
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[OtpViewModel::class.java]

        // Observe enteredOTP LiveData and Update Cursor position based on the length of OTP entered by the user
        viewModel.enteredOTP.observe(viewLifecycleOwner) {
            when (it.length) {
                0 -> binding.otpView.editText1.requestFocus()
                1 -> binding.otpView.editText2.requestFocus()
                2 -> binding.otpView.editText3.requestFocus()
                3 -> binding.otpView.editText4.requestFocus()
            }
        }

        // Set TextChangedListeners for EditText fields
        binding.otpView.editText1.addTextChangedListener {
            viewModel.setEnteredOTP(
                "${
                    it?.toString().orEmpty()
                }${binding.otpView.editText2.text}${binding.otpView.editText3.text}${binding.otpView.editText4.text}"
            )
        }

        binding.otpView.editText2.addTextChangedListener {
            viewModel.setEnteredOTP(
                "${binding.otpView.editText1.text}${
                    it?.toString().orEmpty()
                }${binding.otpView.editText3.text}${binding.otpView.editText4.text}"
            )
        }

        binding.otpView.editText3.addTextChangedListener {
            viewModel.setEnteredOTP(
                "${binding.otpView.editText1.text}${binding.otpView.editText2.text}${
                    it?.toString().orEmpty()
                }${binding.otpView.editText4.text}"
            )
        }

        binding.otpView.editText4.addTextChangedListener {
            viewModel.setEnteredOTP(
                "${binding.otpView.editText1.text}${binding.otpView.editText2.text}${binding.otpView.editText3.text}${
                    it?.toString().orEmpty()
                }"
            )
        }


        // Get the OTP value from shared pref
        val mobileNumber = args.mobileNumber


        // Set the mobile number in the text view
        binding.number.text = mobileNumber

        // Set click listener for verify button
        binding.submitOtp.setOnClickListener {
            var result = viewModel.verifyOtp(view.context)

            // Observe the verifyOtp LiveData
            if (result) {
                // Navigate to the next screen
                findNavController().navigate(R.id.action_otpFragment_to_profileFragment)
            } else {
                // Show error message
                Toast.makeText(view.context, "Invalid OTP", Toast.LENGTH_SHORT).show()
            }
        }

        binding.resendOtp.setOnClickListener {
            // Generate OTP using ViewModel
        }

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.editPhone.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}
