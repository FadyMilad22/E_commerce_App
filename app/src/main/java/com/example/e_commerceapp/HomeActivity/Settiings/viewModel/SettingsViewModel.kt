package com.example.e_commerceapp.HomeActivity.Settiings.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepo
import com.example.e_commerceapp.Model.Address
import com.example.e_commerceapp.Model.CharingBalance
import com.example.e_commerceapp.Model.User
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel (private val settingsRepo: SettingsRepo): ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth


    private var _userD = MutableLiveData<User>()
    val userD : LiveData<User> = _userD

    private val _successfulAddAdress = MutableLiveData<Boolean?>(null)
    val successfulAddAdress: LiveData<Boolean?> =  _successfulAddAdress

    private val _successfulCharging = MutableLiveData<Boolean?>(null)
    val successfulCharing: LiveData<Boolean?> =  _successfulCharging

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

    fun getCustomerData(token :String){
        viewModelScope.launch {

            val response=  settingsRepo.getUserData(token.trim())
            Log.i("Fady122","Is Response User Data ? ${token} ")
            Log.i("Fady122","Is Response Successful ?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code ?${response.code()} ")
            Log.i("Fady122","Is Response body ?${response.body()} ")

            if (response.isSuccessful){

                _userD.value = response.body()?.user
            }

        }

    }


    fun addAdress(address: Address,token :String){
        viewModelScope.launch {

            val response=  settingsRepo.addAddress(address,token)
            Log.i("Fady122","Is Response Successful  Add Address?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code  Add Address ?${response.code()} ")
            Log.i("Fady122","Is Response body  Add Address ?${response.body()} ")
            Log.i("Fady122","Is Response body  Add Address ?${response.message()} ")


            _successfulAddAdress.value = response.isSuccessful

        }

    }

    fun chargeBalance(card :CharingBalance ,token :String){
        viewModelScope.launch {

            val response=  settingsRepo.chargeBalance(card,token.trim())
            Log.i("Fady122","Is Response Successful  Add chargeBalance ${response.isSuccessful} ")
            Log.i("Fady122","Is Response code  chargeBalance ?${response.code()} ")
            Log.i("Fady122","Is Response body  chargeBalance ?${response.body()} ")


            _successfulCharging.value = response.isSuccessful


        }

    }











    }

