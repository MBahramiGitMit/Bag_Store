package com.mbahrami.bagstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mbahrami.bagstore.navigation.Navigation
import com.mbahrami.bagstore.ui.theme.BagStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            BagStoreTheme(darkTheme = false, dynamicColor = false) {
                Navigation(navController = navController)
            }
        }
    }
}