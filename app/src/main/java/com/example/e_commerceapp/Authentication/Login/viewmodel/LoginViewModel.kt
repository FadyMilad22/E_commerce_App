package com.example.e_commerceapp.Authentication.Login.viewmodel

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class LoginViewModel : ViewModel() {
//    var _successfullRegister = MutableLiveData<Boolean>(false)
//    val successfullRegister : LiveData<Boolean> =_successfullRegister
 //   private lateinit var binding: LoginViewModel
    private lateinit var firebaseAuth: FirebaseAuth


    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }




}