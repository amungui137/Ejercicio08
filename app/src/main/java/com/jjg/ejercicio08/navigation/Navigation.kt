package com.jjg.ejercicio08.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjg.ejercicio08.screens.SplashScreen
import com.jjg.ejercicio08.screens.login.LoginScreen
import com.jjg.ejercicio08.screens.home.Home
import com.jjg.ejercicio08.Screens


@Composable
fun Navigation(){
    val navController= rememberNavController()
    NavHost(
        navController=navController,
        startDestination = Screens.SplashScreen.name
    ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(Screens.HomeScreen.name){
            Home(navController = navController)
        }
    }
}