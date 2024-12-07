package com.mbahrami.bagstore.ui.screen.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
        Toast.makeText(context, "sign in", Toast.LENGTH_SHORT).show()
    }

    fun signUp() {
        Toast.makeText(context, "sign up", Toast.LENGTH_SHORT).show()
    }

}