package com.example.loginwithcomposable.utils


object ValidationUtils {

    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    val passwordRegex = Regex("^.{6,}$")
    val phoneRegex = Regex("^[0-9]{10}$")

    fun isValidPhone(phone: String): Boolean {
        return phoneRegex.matches(phone)
    }

    fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return passwordRegex.matches(password)
    }
}