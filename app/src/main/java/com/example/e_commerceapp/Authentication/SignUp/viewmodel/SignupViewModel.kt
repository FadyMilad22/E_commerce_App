package com.example.e_commerceapp.Authentication.SignUp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

  private var _successfullRegister = MutableLiveData<Boolean?>(null)
    val successfullRegister : LiveData<Boolean?> =_successfullRegister
    private lateinit var firebaseAuth: FirebaseAuth


    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }



    fun RegisterUserFirebase(email :String , pass: String , name :String , phoneNumber: String){

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                _successfullRegister.value = it.isSuccessful
            } }
    }











}