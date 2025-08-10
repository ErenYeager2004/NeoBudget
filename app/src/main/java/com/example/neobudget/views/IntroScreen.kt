package com.example.neobudget.views

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.neobudget.R
import kotlinx.coroutines.delay

@Composable
fun IntroScreen(
    modifier: Modifier,
    navController: NavHostController,
    authViewModel: com.example.neobudget.model.AuthViewModel
) {
    var showTitle by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    // Start animation when screen loads
    LaunchedEffect(Unit) {
        showTitle = true
        delay(300) // delay for button
        showButton = true
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (image, gradient, title, btn) = createRefs()

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.intro_pic),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        // Gradient overlay at the bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0x66000000)),
                        startY = 400f
                    )
                )
                .constrainAs(gradient) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        // Slide-in Title (from left)
        AnimatedVisibility(
            visible = showTitle,
            enter = slideInHorizontally(
                initialOffsetX = { -it }, // from left
                animationSpec = tween(durationMillis = 700)
            ),
            modifier = Modifier.constrainAs(title) {
                bottom.linkTo(btn.top, margin = 58.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text(
                text = "NeoBudget\nThe Best App to\nManage Your Money",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 35.dp)
            )
        }

        // Slide-in Button (from right)
        AnimatedVisibility(
            visible = showButton,
            enter = slideInHorizontally(
                initialOffsetX = { it }, // from right
                animationSpec = tween(durationMillis = 700)
            ),
            modifier = Modifier.constrainAs(btn) {
                start.linkTo(parent.start, margin = 32.dp)
                bottom.linkTo(parent.bottom, margin = 48.dp)
            }
        ) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .background(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable {
                        navController.navigate("login"){
                            popUpTo("intro") { inclusive = false } // keep intro in the back stack
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}