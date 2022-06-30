package com.example.getchatt.presentation.screens

sealed class Screens(val route:String){
    object GHomeScreen : Screens(route = "home_screen")
    object GRegistrationScreen : Screens(route = "registration_screen")
    object GLoginScreen : Screens(route = "login_screen")
    object GChattListScreen : Screens(route = "chat_list_screen")
    object Settings : Screens(route = "settings_screens")
}
