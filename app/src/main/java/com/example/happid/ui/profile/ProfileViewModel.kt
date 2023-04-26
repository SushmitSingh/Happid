package com.example.happid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.happid.model.ProfileData
import com.example.happid.network.ApiResponse
import com.example.happid.repository.HappidRepo

class ProfileViewModel : ViewModel() {
    private val profileRepository = HappidRepo()
    private val _createProfileLiveData = MutableLiveData<ApiResponse<String>>()
    val createProfileLiveData: LiveData<ApiResponse<String>> get() = _createProfileLiveData

    fun createProfile(formData: ProfileData) {
        //Validate form data
        if (formData.firstName.isEmpty() || formData.lastName.isEmpty() || formData.phone.isEmpty() ) {
            _createProfileLiveData.value = ApiResponse.error("Please fill required fields", null)
        } else {
            _createProfileLiveData.value = ApiResponse.loading()
            profileRepository.createProfile(formData).observeForever { apiResponse ->
                _createProfileLiveData.value = apiResponse
            }
        }
    }
}
