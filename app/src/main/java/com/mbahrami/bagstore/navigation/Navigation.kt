package com.mbahrami.bagstore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mbahrami.bagstore.navigation.destination.AuthScr
import com.mbahrami.bagstore.navigation.destination.authenticationScreenComposable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AuthScr) {
        authenticationScreenComposable(navController=navController)
    }
}