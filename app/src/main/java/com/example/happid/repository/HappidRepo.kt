package com.example.happid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.happid.model.ProfileData
import com.example.happid.network.ApiResponse
import com.example.happid.network.ApiService
import com.example.happid.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HappidRepo {
    private val apiService: ApiService = NetworkClient.apiService

    fun createProfile(formData: ProfileData): LiveData<ApiResponse<String>> {
        val createProfileLiveData = MutableLiveData<ApiResponse<String>>()
        createProfileLiveData.value = ApiResponse.loading()

        apiService.createProfile(formData).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // Check if response body is not null
                    response.body()?.let { responseBody ->
                        createProfileLiveData.value = ApiResponse.success(responseBody)
                    } ?: run {
                        // Handle null response body
                        createProfileLiveData.value = ApiResponse.error("Empty response body", null)
                    }
                } else {
                    createProfileLiveData.value = ApiResponse.error(response.message(), null)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                createProfileLiveData.value = ApiResponse.error(t.localizedMessage, null)
            }
        })

        return createProfileLiveData
    }
}
