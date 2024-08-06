package com.ylcd.logincompose.util

class Validations {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhoneNumber(phone: String): Boolean {
        val regex = """\+\d{1,3} \d{8,9}""".toRegex()
        return regex.matches(phone) && phone.substring(1).replace(" ", "")
            .all { it.isDigit() }
    }


    fun isValidPassword(password: String): Boolean {
        // Regex to check the password format: at least one lowercase letter, one uppercase letter,
        // one digit, and one special character, and a minimum length of 8 characters
        val regex =
            """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#\$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$"""
                .toRegex()
        return regex.matches(password)
    }

    fun passwordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }



}