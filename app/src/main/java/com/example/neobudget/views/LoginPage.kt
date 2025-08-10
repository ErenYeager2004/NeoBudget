package com.example.neobudget.views

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.neobudget.model.AuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay


@Composable
fun LoginPage(
    modifier: Modifier,
    navController: NavController,
    authViewModel: com.example.neobudget.model.AuthViewModel
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showLoading by remember { mutableStateOf(false) }
    var showLoadingAfterLogin by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    // Navigation after login success
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                showLoadingAfterLogin = true
                delay(1000)
                showLoadingAfterLogin = false
                navController.navigate("home"){
                    popUpTo("intro") { saveState = true } // keeps intro in the back stack
                    launchSingleTop = true
                    restoreState = true
                }
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState.value as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }

    // Navigation to signup page
    if (showLoading) {
        LaunchedEffect(Unit) {
            delay(1000)
            showLoading = false
            navController.navigate("signup")
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading || showLoadingAfterLogin) {
            CircularProgressIndicator()
        } else {
            Text(text = "Welcome Back!", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                authViewModel.login(email, password)
            }) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Don't have an account? ")
                Text(
                    text = "SignUp",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        showLoading = true
                    }
                )
            }
        }
    }
}