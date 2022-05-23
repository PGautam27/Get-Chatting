package com.example.getchatt.presentation.screens

sealed class Screens(route:String){
    object GHomeScreen : Screens(route = "home_screen")
    object GRegistrationScreen : Screens(route = "registration_screen")
    object GLoginScreen : Screens(route = "login_screen")
}
