package com.example.e_commerceapp.Authentication.SignUp.viewmodel

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.SignUp.Repo.SignupRepo
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Seller
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel (private val signupRepo: SignupRepo): ViewModel() {

  private var _successfullRegister = MutableLiveData<Boolean?>(null)
    val successfullRegister : LiveData<Boolean?> =_successfullRegister
    private lateinit var firebaseAuth: FirebaseAuth


    private var _token = MutableLiveData<String?>()
    val token : LiveData<String?> =_token



    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }



    fun RegisterUserFirebase(email :String , pass: String , name :String , phoneNumber: String, context: Context){

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {

                if (it.isSuccessful){

                   regesterApiCutomer(name,phoneNumber, context )

                }

            } }
    }



    private fun regesterApiCutomer(name: String, phoneNumber: String, context: Context){

        viewModelScope.launch(Dispatchers.IO) {


            Log.i("Fady1234","${firebaseAuth.currentUser?.uid}" )
            val user = Customer(firebaseAuth.currentUser?.uid, name, phoneNumber)

            val response= signupRepo.regestercustomer(user)
            if (response.isSuccessful){
                viewModelScope.launch {
                    _token.value = response.body()!!.data!!.accessToken
                    _successfullRegister.value = response.isSuccessful
                    Log.i("Fady12345","${response.isSuccessful}" )
                    Log.i("Fady12345","${response.body()}" )}
            }else{
                val builder = MaterialAlertDialogBuilder(context)
                builder.setTitle("Network Error")
                builder.setMessage("Due to Network error!")

// **Important:** Create an instance of an OnClickListener
                val clickListener = DialogInterface.OnClickListener { dialog, which ->
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        regesterApiCutomer(name , phoneNumber,context) // Call your function here
                    }
                }

                builder.setPositiveButton("Try again", clickListener)
                builder.show()
            }

        }

    }



     fun RegisterSellerFirebase(email :String , pass: String , name :String , context: Context){

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful){

                    viewModelScope.launch(Dispatchers.IO) {

                        Log.i("Fad1234","${firebaseAuth.currentUser?.uid}" )

                        regesterAPISeller(name, context)

                    }

                }

            } }
    }






 private fun regesterAPISeller(name:String, context:Context){


     viewModelScope.launch(){
         val user = Seller(firebaseAuth.currentUser?.uid, name)

         val response= signupRepo.regesterSeller(user)
         if (response.isSuccessful){
             _token.value = response.body()!!.data!!.accessToken
             Log.i("Fad12345Sellersignup","${response.body()!!.data!!.accessToken}" )
             _successfullRegister.value = response.isSuccessful

             Log.i("Fad12345","${response.isSuccessful}" )
             Log.i("Fad12345","${response.body()}" )}
     else{

             val builder = MaterialAlertDialogBuilder(context)
             builder.setTitle("Network Error")
             builder.setMessage("Due to Network error!")

// **Important:** Create an instance of an OnClickListener
             val clickListener = DialogInterface.OnClickListener { dialog, which ->
                 if (which == DialogInterface.BUTTON_POSITIVE) {
                     regesterAPISeller(name , context) // Call your function here
                 }
             }

             builder.setPositiveButton("Try again", clickListener)
             builder.show()
     }


     }


 }


}