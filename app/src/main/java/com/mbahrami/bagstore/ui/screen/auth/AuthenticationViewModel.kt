package com.mbahrami.bagstore.ui.screen.auth

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mbahrami.bagstore.util.checkForInternet
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModel() {
    var fullName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    fun onFullNameChange(newValue: String) {
        fullName = newValue
    }

    fun onEmailChange(newValue: String) {
        email = newValue
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
    }

    fun onConfirmPasswordChange(newValue: String) {
        confirmPassword = newValue
    }

    fun signIn() {
        if (signInValidateField()) {
            if (checkForInternet(context = context)) {
                displayToast(context, "signIn")
            } else {
                displayToast(context, "Check your connection.")
            }
        }
    }

    fun signUp() {
        if (signUpValidateField()) {
            if (checkForInternet(context = context)) {
                displayToast(context, "signUp")
            } else {
                displayToast(context, "Check your connection.")
            }
        }
    }

    private fun signUpValidateField(): Boolean {
        val isNameValid = fullName.isNotEmpty()
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8
        val isConfirmPasswordCorrect = confirmPassword == password
        if (!isNameValid) {
            displayToast(context, "FullName is Empty!")
        } else if (!isEmailValid) {
            displayToast(context, "Email is wrong!")
        } else if (!isPasswordValid) {
            displayToast(context, "Password has to be more than 8 Character!")
        } else if (!isConfirmPasswordCorrect) {
            displayToast(context, "Confirm password is not correct")
        }
        return isNameValid && isEmailValid && isPasswordValid && isConfirmPasswordCorrect
    }

    private fun signInValidateField(): Boolean {
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8
        if (!isEmailValid) {
            displayToast(context, "Email is wrong!")
        } else if (!isPasswordValid) {
            displayToast(context, "Password has to be more than 8 Character!")
        }
        return isEmailValid && isPasswordValid
    }

}