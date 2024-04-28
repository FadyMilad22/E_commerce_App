package com.example.e_commerceapp.HomeActivity.Settiings.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepo
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel (private val settingsRepo: SettingsRepo): ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth

    fun  InitFirebase(){
        viewModelScope.launch {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }


    fun logout(){


// Logout button click listener

        viewModelScope.launch {
            firebaseAuth.signOut()
            if(firebaseAuth.currentUser == null){
               Log.i("FadyF","currentuser is null")

            }
        }


        }

    }

