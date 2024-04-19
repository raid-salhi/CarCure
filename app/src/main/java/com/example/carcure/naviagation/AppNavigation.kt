package com.example.carcure.naviagation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carcure.screens.MainScreen
import com.example.carcure.screens.MainViewModel
import com.example.carcure.screens.ResultScreen
import com.example.carcure.screens.SplashScreen

@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    val mainViewModel : MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name){
        composable(route = Screens.SplashScreen.name ){
            SplashScreen(navController)
        }
        composable(route = Screens.MainScreen.name ){
            MainScreen(navController,mainViewModel)
        }
        composable(route = Screens.ResultScreen.name ){
            ResultScreen(navController, mainViewModel = mainViewModel)
        }
    }
}