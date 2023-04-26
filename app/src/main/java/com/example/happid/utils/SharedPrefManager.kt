package com.example.happid.utils

import android.content.Context

object SharedPrefManager {

    private const val PREF_NAME = "MyPrefs"

    // Method for saving OTP in shared preferences
    fun saveOtp(context: Context, otp: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("OTP", otp).apply()
    }

    // Method for retrieving OTP from shared preferences
    fun getOtp(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("OTP", null)
    }

    // Method for saving user session status in shared preferences
    fun saveUserSession(context: Context, isLoggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("IS_LOGGED_IN", isLoggedIn).apply()
    }

    // Method for retrieving user session status from shared preferences
    fun isUserLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false)
    }

    // Method for saving splash completion status in shared preferences
    fun saveSplashCompletion(context: Context, isCompleted: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("IS_SPLASH_COMPLETED", isCompleted).apply()
    }

    // Method for retrieving splash completion status from shared preferences
    fun isSplashCompleted(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IS_SPLASH_COMPLETED", false)
    }

    // Method for saving login completion status in shared preferences
    fun saveLoginCompletion(context: Context, isCompleted: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("IS_LOGIN_COMPLETED", isCompleted).apply()
    }

    // Method for retrieving login completion status from shared preferences
    fun isLoginCompleted(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IS_LOGIN_COMPLETED", false)
    }

}
