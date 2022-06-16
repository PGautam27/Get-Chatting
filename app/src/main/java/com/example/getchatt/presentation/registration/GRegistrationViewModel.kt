package com.example.getchatt.presentation.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.coroutineContext

class GRegistrationViewModel:ViewModel() {
    private val auth = Firebase.auth
    private val _sucessOrfail = MutableLiveData<Boolean>()
    val sucessOrfail: LiveData<Boolean> = _sucessOrfail
    fun register(email: String, password: String,context: AppCompatActivity){
        auth.createUserWithEmailAndPassword(
            email,password
        ).addOnCompleteListener(context) { task ->
            if (task.isSuccessful){
                Log.d("AUTH", "Success!")
                _sucessOrfail.value = true
            } else {
                Log.w("SignUpWithEmail:Failure", task.exception)
                Toast.makeText(
                    context, "You Already Have an account Or Wrong credentials",
                    Toast.LENGTH_SHORT
                ).show()
                _sucessOrfail.value = false
            }
        }
    }
}