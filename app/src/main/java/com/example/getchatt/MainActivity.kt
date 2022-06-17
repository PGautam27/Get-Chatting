package com.example.getchatt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.getchatt.presentation.GHomeScreen
import com.example.getchatt.presentation.login.GLoginScreen
import com.example.getchatt.presentation.registration.GRegistrationScreen
import com.example.getchatt.presentation.screens.Screens
import com.example.getchatt.ui.theme.GetChattTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetChattTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.GHomeScreen.route){
                    composable(
                        Screens.GHomeScreen.route,
                    ){
                        GHomeScreen(navController)
                    }
                    composable(Screens.GRegistrationScreen.route){
                        GRegistrationScreen(navController, context = this@MainActivity)
                    }
                    composable(
                        Screens.GLoginScreen.route
                    ){
                        GLoginScreen(navController)
                    }
                }
            }
        }
    }
}
