package com.mbahrami.bagstore.ui.screen.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(authVM: AuthenticationViewModel) {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Background()
            AuthFormContent(
                fullName = { authVM.fullName },
                onFullNameChange = {
                    authVM.onFullNameChange(it)
                },
                email = { authVM.email },
                onEmailChange = { authVM.onEmailChange(it) },
                password = { authVM.password },
                onPasswordChange = { authVM.onPasswordChange(it) },
                confirmPassword = { authVM.confirmPassword },
                onConfirmPasswordChange = { authVM.onConfirmPasswordChange(it) },
                onSingInClick = { authVM.signIn() },
                onSingUpClick = { authVM.signUp() }
            )
        }
    }
}

@Composable
fun Background() {
    val height = LocalConfiguration.current.screenHeightDp.times(0.4f).dp
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

