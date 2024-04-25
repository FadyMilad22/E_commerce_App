package com.example.e_commerceapp.Authentication.SignUp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModel
import com.example.e_commerceapp.Authentication.SignUp.Repo.SignupRepo
import com.example.e_commerceapp.Model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel (private val signupRepo: SignupRepo): ViewModel() {

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
                if (it.isSuccessful){

                    viewModelScope.launch(Dispatchers.IO) {

Log.i("Fad1234","${firebaseAuth.currentUser?.uid}" )
                        val user = User(firebaseAuth.currentUser?.uid, name, phoneNumber)

                       val response= signupRepo.regestercustomer(user)
                        Log.i("Fad12345","${response.isSuccessful}" )
                        Log.i("Fad12345","${response.body()}" )
                    }

                }

            } }
    }











}