package com.example.e_commerceapp.HomeActivity.cart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.User
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepo
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeSellerViewModel (private val homeSellerRepo: HomeSellerRepo): ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth

    private var _token = MutableLiveData<String?>()
    val token : LiveData<String?> =_token

    private var _itemsOfthisSeller = MutableLiveData<List<Item>>()
    val itemsOfthisSeller : LiveData<List<Item>> =_itemsOfthisSeller

    private var _userD = MutableLiveData<User>()
    val userD : LiveData<User> = _userD



    fun setStringData(data: String) {
        _token.value = data
    }


    fun getItemsOfThisSeller(token :String){
        viewModelScope.launch {

            val response=  homeSellerRepo.getItemsofThisSeller(token.trim())
            Log.i("Fady122","Is Response token ? ${token} ")
            Log.i("Fady122","Is Response Successful ?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code ?${response.code()} ")
            Log.i("Fady122","Is Response body ?${response.body()} ")

            if (response.isSuccessful){

                _itemsOfthisSeller.value = response.body()?.items
            }

        }

    }



    fun getUserData(token :String){
        viewModelScope.launch {

            val response=  homeSellerRepo.getUserData(token.trim())
            Log.i("Fady122","Is Response User Data ? ${token} ")
            Log.i("Fady122","Is Response Successful ?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code ?${response.code()} ")
            Log.i("Fady122","Is Response body ?${response.body()} ")

            if (response.isSuccessful){

                _userD.value = response.body()?.user
            }

        }

    }

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