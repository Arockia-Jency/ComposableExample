package com.example.loginwithcomposable.helper

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveLogin(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    fun clearSession() {
        prefs.edit()
            .putBoolean("is_logged_in", false)
            .apply()
    }

    fun saveUser(email: String, password: String) {
        prefs.edit()
            .putString("email", email)
            .putString("password", password)
            .apply()
    }

    fun getEmail(): String {
        return prefs.getString("email", "") ?: ""
    }

    fun getPassword(): String {
        return prefs.getString("password", "") ?: ""
    }



    fun saveRememberMe(isChecked: Boolean) {
        prefs.edit().putBoolean("remember_me", isChecked).apply()
    }

    fun isRememberMeChecked(): Boolean {
        return prefs.getBoolean("remember_me", false)
    }
}