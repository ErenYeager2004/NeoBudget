package com.example.neobudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.neobudget.ui.theme.NeoBudgetTheme
import com.example.neobudget.views.IntroScreen // Import your IntroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeoBudgetTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    IntroScreen(
                        onStartClick = {
                            // Handle button click here
                            // e.g., navigate to another screen
                        }
                    )
                }
            }
        }
    }
}
