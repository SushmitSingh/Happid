package com.example.happid.ui.otp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.happid.utils.SharedPrefManager

class OtpViewModel : ViewModel() {
    private val _enteredOTP = MutableLiveData<String>()
    val enteredOTP: LiveData<String> get() = _enteredOTP

    fun verifyOtp(context: Context): Boolean {
        //Otp is FirstAndLastDigitOfMobileNumber is Saved in SharedPrefManager
        val savedOtp = SharedPrefManager.getOtp(context)
        return savedOtp == enteredOTP.value
    }

    fun setEnteredOTP(otp: String) {
        _enteredOTP.postValue(otp)
    }
}
