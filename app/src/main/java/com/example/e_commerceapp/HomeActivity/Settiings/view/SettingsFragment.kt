package com.example.e_commerceapp.HomeActivity.Settiings.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepoImpl
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModel
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {



    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
     gettingViewModelReady()

        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView6.setOnClickListener(){
            viewModel.InitFirebase()
            viewModel.logout()
        }
    }





    private fun gettingViewModelReady(){
        val factory = SettingsViewModelFactory(SettingsRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[SettingsViewModel::class.java]
    }
}