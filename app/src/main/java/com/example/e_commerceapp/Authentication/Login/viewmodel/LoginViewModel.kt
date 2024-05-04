package com.example.e_commerceapp.Authentication.Login.viewmodel


import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.Login.Repo.LoginRepo
import com.example.e_commerceapp.Model.DataXX
import com.example.e_commerceapp.Model.LoginRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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



 fun loginUserFirebase(email :String , pass:String , context: Context){
     viewModelScope.launch {

     firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {

         if (it.isSuccessful) {
             Log.i("Fady1", "login success")
             LogintoAPI(context = context)
                 }
             }
         } }




   fun LogintoAPI(context :Context){

       viewModelScope.launch() {
           Log.i("Fady1", firebaseAuth.currentUser!!.uid)

           val response = loginRepo.loginUser(LoginRequest(firebaseAuth.currentUser!!.uid))
           Log.i("Fady1", "UID success")

           Log.i("Fady1", "${response.body()}")
           if (response.isSuccessful) {

               _user.value = (response.body()?.data)
               _successfullLogin.value = true
           }else{
               val builder = MaterialAlertDialogBuilder(context)
               builder.setTitle("Network Error")
               builder.setMessage("Due to Network error!")

// **Important:** Create an instance of an OnClickListener
               val clickListener = DialogInterface.OnClickListener { dialog, which ->
                   if (which == DialogInterface.BUTTON_POSITIVE) {
                       LogintoAPI(context) // Call your function here
                   }
               }

               builder.setPositiveButton("Try again", clickListener)
               builder.show()
           }
       }


   }

    fun alreadyLoggedIn(context: Context){


     if(firebaseAuth.currentUser != null){
         MaterialAlertDialogBuilder(context).setTitle("Logging you In ").setMessage("Please wait").show()
         LogintoAPI(context)

     }







}

}