package com.example.e_commerceapp.HomeActivity.cart.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.History.Repo.cartRepo
import com.example.e_commerceapp.Model.Cart
import com.example.e_commerceapp.Model.CartItem
import kotlinx.coroutines.launch

class CartViewModel (private val cartRepo: cartRepo): ViewModel() {


    private val _cart = MutableLiveData<Cart?>()
    val cart: LiveData<Cart?> =  _cart

    private val _successfulEdit = MutableLiveData<Boolean>()
    val successfulEdit: LiveData<Boolean> =  _successfulEdit

    private val _successfulDelete = MutableLiveData<Boolean>()
    val successfulDelete: LiveData<Boolean> =  _successfulDelete


    private val _successfulOrder = MutableLiveData<Boolean?>(null)

    val successfulOrder: LiveData<Boolean?> =  _successfulOrder


fun getCart(token :String){

    viewModelScope.launch {

        val response = cartRepo.getCart(token)
        Log.i("Fady1212","Message get cart :${response.body()?.message} \n body :${response.body()?.items} \n Code: ${response.code()}")

        Log.i("Fady1212","Message get cart :${response.body()?.order}")

        if (response.isSuccessful) {

        _cart.value  =  response.body()

        }
    }

}


    fun editquantity(cartItem: CartItem,token :String ){

        viewModelScope.launch {


            val response = cartRepo.editquantity(cartItem, token)
            Log.i("Fady1212","Message edit :${response.body()?.message} \n  Code: ${response.code()}")

            Log.i("Fady1212","Message Order Data:${response.body()?.orderData} \n ")

            if (response.isSuccessful) {
                _successfulEdit.value = true
                getCart(token)

            }else

                _successfulEdit.value = false
        }

    }
    fun deleteItem(itemId : Int,token :String){

        viewModelScope.launch {

            val response = cartRepo.deleteitem(itemId,token)
            Log.i("Fady1212","Message delete :${response.body()?.message} \n Code: ${response.code()}")
            if (response.isSuccessful) {

               _successfulDelete.value = true

                getCart(token)
            }
        }

    }


    fun confirmOrder(token :String , context: Context){


        viewModelScope.launch {

            val response = cartRepo.confirmOrder(token)
            Log.i("Fady1212","Message confirm Order :${response.body()?.message} \n Code: ${response.code()}")




            if (response.isSuccessful){
                getCart(token)
                _successfulOrder.value = true
                Toast.makeText(context,"Order Confirmed Check you Order's List", Toast.LENGTH_SHORT).show()

            }else if (response.code().equals(400)){
                _successfulOrder.value = false

            }else{
                _successfulOrder.value = false
            }


        }

    }





}