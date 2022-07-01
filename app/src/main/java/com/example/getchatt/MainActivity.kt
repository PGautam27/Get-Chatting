package com.example.getchatt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.getchatt.presentation.GHomeScreen
import com.example.getchatt.presentation.GViewModel
import com.example.getchatt.presentation.GViewModelFactory
import com.example.getchatt.presentation.chatt.GChattListScreen
import com.example.getchatt.presentation.chatt.Settings
import com.example.getchatt.presentation.login.GLoginScreen
import com.example.getchatt.presentation.registration.GRegistrationScreen
import com.example.getchatt.presentation.screens.Screens
import com.example.getchatt.ui.theme.GetChattTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetChattTheme {
                BackHandler(enabled = true){ Toast.makeText(this,"", Toast.LENGTH_SHORT).show()}
                val viewModel = GViewModel(application)
                var value = if (viewModel.readUid == null) Screens.GHomeScreen.route else Screens.GChattListScreen.route
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = value){
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
                        GLoginScreen(navController,this@MainActivity,viewModel)
                    }
                    composable(
                        Screens.GChattListScreen.route
                    ) {
                        GChattListScreen(navController)
                    }
                    composable(Screens.Settings.route){
                        Settings(navController,viewModel)
                    }
                }
            }
        }
    }
}
