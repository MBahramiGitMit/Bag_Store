package com.mbahrami.bagstore.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mbahrami.bagstore.ui.screen.auth.AuthenticationScreen
import kotlinx.serialization.Serializable

@Serializable
object AuthScr

fun NavGraphBuilder.authenticationScreenComposable(navController: NavHostController) {
    composable<AuthScr> {
        AuthenticationScreen()
    }
}