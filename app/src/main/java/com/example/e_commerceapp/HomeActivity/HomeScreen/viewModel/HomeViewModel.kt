package com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepo
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.User
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val homeRepo : HomeRepo) : ViewModel() {

    private var _tokenC = MutableLiveData<String?>()
    val tokenC : LiveData<String?> =_tokenC


    private var _userD = MutableLiveData<User>()
    val userD : LiveData<User> = _userD


    private val _exclusiveDeals = MutableLiveData<List<Item>>()
    val exclusiveDeals: LiveData<List<Item>> =  _exclusiveDeals


    private val _hotPrices = MutableLiveData<List<Item>>()
    val hotPrices: LiveData<List<Item>> = _hotPrices




    fun setStringData(data: String) {
        _tokenC.value = data
    }



    fun getExclusiveDeals() {
        viewModelScope.launch {
            val response = homeRepo.getItemsofCategory("Electronics")
            if (response.isSuccessful) {
                 _exclusiveDeals.value = response.body()?.items
                Log.i("Fady1212","Message :${response.body()?.message} \n body :${response.body()?.items} \n Code: ${response.code()}")
            }
            }
        }

        fun getHotDeals() {
            viewModelScope.launch {
                val response = homeRepo.getItemsofCategory("Clothing")
                if (response.isSuccessful) {

                _hotPrices.value = response.body()?.items


                }
            }
        }


    fun getCustomerData(token :String){
        viewModelScope.launch {

            val response=  homeRepo.getUserData(token.trim())
            Log.i("Fady122","Is Response User Data ? ${token} ")
            Log.i("Fady122","Is Response Successful ?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code ?${response.code()} ")
            Log.i("Fady122","Is Response body ?${response.body()} ")

            if (response.isSuccessful){

                _userD.value = response.body()?.user
            }

        }

    }


}