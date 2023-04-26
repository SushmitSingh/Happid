package com.example.happid.network

import com.example.happid.model.ProfileData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("profile/create")
    fun createProfile(@Body formData: ProfileData): Call<String>
}
