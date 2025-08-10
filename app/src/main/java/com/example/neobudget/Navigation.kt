package com.example.neobudget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neobudget.views.HomePage
import com.example.neobudget.views.IntroScreen
import com.example.neobudget.views.LoginPage
import com.example.neobudget.views.SignUpPage


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    authViewModel: com.example.neobudget.model.AuthViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "intro", builder = {
        composable("intro") {
            IntroScreen(modifier,navController,authViewModel)
        }
        composable("login") {
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup") {
            SignUpPage(modifier,navController,authViewModel)
        }
        composable("home") {
            HomePage(modifier ,navController,authViewModel)
        }
    })
}

