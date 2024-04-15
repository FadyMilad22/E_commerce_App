package com.example.e_commerceapp.Authentication.SignUp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

  private var _successfullRegister = MutableLiveData<Boolean>(false)
    val successfullRegister : LiveData<Boolean> =_successfullRegister
    //private lateinit var binding: SignupViewModelBinding
    private lateinit var firebaseAuth: FirebaseAuth

    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }




    fun RegisterUserFirebase(email :String , pass: String , name :String , phoneNumber: String){
//     val email = binding.emailEt.text.toString()
//     val pass = binding.passET.text.toString()
//     val confirmPass = binding.confirmPassEt.text.toString()

//     if (email.isNotEmpty() && pass.isNotEmpty()) {
        // if (pass == confirmPass) {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    _successfullRegister.value = true
                } else {
                    _successfullRegister.value=false
                    //Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
//         } else {
//             Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
//         }
//     } else {
//         Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
//
//     }
        }}
    //}





}