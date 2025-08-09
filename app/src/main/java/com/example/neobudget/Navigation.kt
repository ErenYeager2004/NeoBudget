//package com.example.neobudget
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
////import com.example.neobudget.views.LoginPage
////import com.example.neobudget.views.SignUpPage
//import com.example.neobudget.model.AuthViewModel
////import com.example.neobudget.views.HomePage
//
//@Composable
//fun Navigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel){
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login", builder = {
//        composable("login") {
//            LoginPage(modifier,navController,authViewModel)
//        }
//        composable("signup") {
//            SignUpPage(modifier,navController,authViewModel)
//        }
//        composable("home") {
//            HomePage(modifier,navController,authViewModel)
//        }
//    })
//}