package com.example.e_commerceapp.Authentication.Login.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.Login.Repo.LoginRepo
import com.example.e_commerceapp.Model.DataXX
import com.example.e_commerceapp.Model.LoginRequest

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class LoginViewModel(private val loginRepo: LoginRepo) : ViewModel() {
  private var _successfullLogin = MutableLiveData<Boolean?>(null)
    val successfullLogin : LiveData<Boolean?> =_successfullLogin
 //   private lateinit var binding: LoginViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private var _user = MutableLiveData<DataXX>()
    val user : LiveData<DataXX> =_user




    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }



 fun loginUserFirebase(email :String , pass:String){
     viewModelScope.launch {

     firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {

         if (it.isSuccessful) {
             Log.i("Fady1", "login success")
             LogintoAPI()
                 }
             }
         } }




   fun LogintoAPI(){

       viewModelScope.launch() {
           Log.i("Fady1", firebaseAuth.currentUser!!.uid)

           val response = loginRepo.loginUser(LoginRequest(firebaseAuth.currentUser!!.uid))
           Log.i("Fady1", "UID success")
           Log.i("Fady1", "${response.body()}")
           if (response.isSuccessful) {

               _user.value = (response.body()?.data)
               _successfullLogin.value = true
           }
       }


   }

    fun alreadyLoggedIn(){


     if(firebaseAuth.currentUser != null){

         LogintoAPI()

     }







}

}