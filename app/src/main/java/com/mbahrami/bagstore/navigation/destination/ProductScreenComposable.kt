package com.mbahrami.bagstore.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data class ProductScr(val productId: Int)

fun NavGraphBuilder.productScreenComposable() {
    composable<ProductScr> {backStackEntry->
        val productScr:ProductScr = backStackEntry.toRoute()
//        ProductScreen(productId=productScr.productId)
    }
}
