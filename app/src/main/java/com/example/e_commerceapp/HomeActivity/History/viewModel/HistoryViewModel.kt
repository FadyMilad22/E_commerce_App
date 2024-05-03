package com.example.e_commerceapp.HomeActivity.History.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.History.Repo.HistoryRepo
import com.example.e_commerceapp.Model.Cart
import com.example.e_commerceapp.Model.History
import com.example.e_commerceapp.Model.HistoryResponse
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepo: HistoryRepo) : ViewModel() {



    private val _historyList = MutableLiveData<List<History>>()
    val histortList: LiveData<List<History>> =  _historyList




    fun getHistory(token :String){

        viewModelScope.launch {

            val response = historyRepo.getHistory(token)
            Log.i("Fady1212","Message get History :${response.body()?.message} } \n Code: ${response.code()}")

            Log.i("Fady1212","Message get History :${response.body()?.message}")

            if (response.isSuccessful) {

                _historyList.value  =  response.body()?.history

            }
        }

    }




}