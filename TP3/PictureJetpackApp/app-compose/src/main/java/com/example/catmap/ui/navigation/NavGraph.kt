package com.example.catmap.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmap.model.ImageItem
import com.example.catmap.ui.screens.DetailScreen
import com.example.catmap.ui.screens.FavoritesScreen
import com.example.catmap.ui.screens.MainScreen
import com.example.catmap.ui.viewmodel.DetailViewModel
import com.example.catmap.ui.viewmodel.FavoritesViewModel
import com.example.catmap.ui.viewmodel.MainViewModel
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{imageJson}") {
        fun createRoute(image: ImageItem): String {
            val json = Gson().toJson(image)
            val encodedJson = java.net.URLEncoder.encode(json, "UTF-8")
            return "detail/$encodedJson"
        }
    }
    object Favorites : Screen("favorites")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            val viewModel: MainViewModel = viewModel()
            MainScreen(
                viewModel = viewModel,
                onImageClick = { image ->
                    navController.navigate(Screen.Detail.createRoute(image))
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val encodedJson = backStackEntry.arguments?.getString("imageJson")
            val imageJson = encodedJson?.let { java.net.URLDecoder.decode(it, "UTF-8") }
            val image = imageJson?.let { Gson().fromJson(it, ImageItem::class.java) }
            val viewModel: DetailViewModel = viewModel()
            image?.let { viewModel.setImage(it) }
            
            DetailScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Favorites.route) {
            val viewModel: FavoritesViewModel = viewModel()
            FavoritesScreen(
                viewModel = viewModel,
                onImageClick = { image ->
                    navController.navigate(Screen.Detail.createRoute(image))
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
