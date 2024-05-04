package com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepo
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.User
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val homeRepo : HomeRepo) : ViewModel() {

    private var _tokenC = MutableLiveData<String?>()
    val tokenC : LiveData<String?> =_tokenC


    private val _sucssesfulAddingToCart = MutableLiveData<Boolean?>(null)
    val succesfulAdding: LiveData<Boolean?> =  _sucssesfulAddingToCart

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




    fun addItemToCart(item: CartItem, token :String ){
        viewModelScope.launch {

            val response = homeRepo.AddItemToCart(item, token)
            Log.i("Fady5122","Message :${response.body()?.message} \nCode: ${response.code()}")
            if(response.isSuccessful){

                _sucssesfulAddingToCart.value = true

            }


        }

    }

    fun returnFalse(){
        viewModelScope.launch(){
            _sucssesfulAddingToCart.value= false
        }
    }



}