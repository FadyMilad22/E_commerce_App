package com.example.e_commerceapp.HomeActivity.Settiings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepo




class SettingsViewModelFactory(private val settingsRepo: SettingsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            SettingsViewModel(settingsRepo) as T
        }else{
            throw IllegalArgumentException("settingsViewModel class not found")
        }
    }
}