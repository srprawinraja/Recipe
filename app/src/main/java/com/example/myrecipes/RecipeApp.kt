package com.example.myrecipes

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeApp(
    navController: NavHostController
) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.catergoriesState

    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewState, navigateToDetail ={
                navController.currentBackStackEntry?.savedStateHandle?.set("recipe", it)
                navController.navigate(Screen.DetailScreen.route)
            } )
        }
        composable(Screen.DetailScreen.route){
           var category:Category= navController.previousBackStackEntry?.savedStateHandle?.get<Category>("recipe")?:Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }

}
