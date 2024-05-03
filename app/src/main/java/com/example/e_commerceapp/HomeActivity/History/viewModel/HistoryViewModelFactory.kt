package com.example.e_commerceapp.HomeActivity.History.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.History.Repo.HistoryRepo


class HistoryViewModelFactory(private val historyRepo: HistoryRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(historyRepo) as T
        }else{
            throw IllegalArgumentException("History ViewModel class not found")
        }
    }
}