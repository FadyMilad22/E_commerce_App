package com.example.e_commerceapp.HomeActivity.Settiings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepoImpl
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModel
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModelFactory
import com.example.e_commerceapp.Model.Address
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentAddAddressBinding
import com.example.e_commerceapp.databinding.FragmentChargeBalanceBinding


class AddAddressFragment : Fragment() {

    private val args : AddAddressFragmentArgs by navArgs()

    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding : FragmentAddAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAddAddressBinding.inflate(inflater,container,false)
        return  binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
gettingViewModelReady()


        binding.buttonlog.setOnClickListener() {


            viewModel.addAdress(Address(binding.inputaddress.text.toString()),args.token!!)

        }

        binding.textView31.text = args.address
        binding.textView10.text = args.name


        viewModel.successfulAddAdress.observe(viewLifecycleOwner){

            if(it ==true){

                binding.textView31.text = binding.inputaddress.text.toString()

            }else if (it == false){

                Toast.makeText(context,"Please Enter the address again",Toast.LENGTH_SHORT).show()
            }

        }



    }




    private fun gettingViewModelReady(){
        val factory = SettingsViewModelFactory(SettingsRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[SettingsViewModel::class.java]
    }
}