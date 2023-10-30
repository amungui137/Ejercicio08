package com.jjg.ejercicio08.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    //la estaremos usando a lo largo del proyecto
    private val auth: FirebaseAuth = Firebase.auth

    //impide que se creen varios usuarios accidentalmente
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("MyLogin", "signInWithEmailAndPassword logueado!!!")
                            home()
                        } else {
                            Log.d(
                                "MyLogin",
                                "signInWithEmailAndPassword: ${task.result.toString()}"
                            )
                        }
                    }
            } catch (ex: Exception) {
                Log.d("MyLogin", "signInWithEmailAndPassword: ${ex.message}")
            }
        }

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value == true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName =
                        //task.result.user?.email?.split("@")
                        //?.get(0) //Extrae el username para el firestore
                            //createUser(displayName)
                            home()
                    } else {
                        Log.d(
                            "MyLogin",
                            "createUserWithEmailAndPassword: ${task.result.toString()}"
                        )
                    }
                    _loading.value = false
                }
        }
    }

    fun signInWithGoogleCredential(credential: AuthCredential, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credential).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        Log.d("MyLogin", "signInWithEmailAndPassword logueado!!!")
                        home()
                    } else {
                        Log.d(
                            "MyLogin",
                            "signInWithEmailAndPassword: ${task.result.toString()}"
                        )
                    }
                }
            } catch (ex: Exception) {
                Log.d("MyLogin", "Error al loguear con Google: ${ex.message}")
            }
        }

}