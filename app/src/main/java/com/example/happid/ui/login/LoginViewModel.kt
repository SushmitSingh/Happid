package com.example.happid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // MutableLiveData for storing the mobile number and OTP
    private val _mobileNumber = MutableLiveData<String>()
    val mobileNumber: LiveData<String> get() = _mobileNumber

    private val _otp = MutableLiveData<String>()
    val otp: LiveData<String> get() = _otp

    // Method for generating OTP
    fun generateOtp(mobileNumber: String) {
        // Generate OTP
        val otp =
            "${mobileNumber.substring(0, 2)}${mobileNumber.substring(mobileNumber.length - 2)}"

        // Update LiveData with generated OTP
        _otp.value = otp
    }
}
