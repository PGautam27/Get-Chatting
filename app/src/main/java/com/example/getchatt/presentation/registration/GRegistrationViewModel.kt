package com.example.getchatt.presentation.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.getchatt.presentation.screens.Screens
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GRegistrationViewModel:ViewModel() {
    private val auth = Firebase.auth
    private val success = MutableLiveData<Boolean>()
    val successOrNot = success

    fun register(email: String,password:String,context: ComponentActivity){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(context) { task->
                if (task.isSuccessful){
                    if (task.isSuccessful){
                        Log.d("AUTH", "Success")
                        success.value = true
                    }
                    else{
                        Log.w("SignUpWithEmail:Failure", task.exception)
                        Toast.makeText(
                            context, "You Already Have an account Or Wrong credentials",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
    }
}