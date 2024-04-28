package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController


import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepoImpl
import com.example.e_commerceapp.databinding.FragmentHomeSellerBinding

class HomeSellerFragment : Fragment() {



    private lateinit var viewModel: HomeSellerViewModel
private lateinit var binding : FragmentHomeSellerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()


        binding =  FragmentHomeSellerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener(){

            Toast.makeText(context, "Not yet Implemented ", Toast.LENGTH_SHORT).show()
        }



        binding.button2.setOnClickListener(){

findNavController().navigate(R.id.action_homeSellerFragment_to_mangeProductsFragment)
        }

        binding.button3.setOnClickListener(){

            Toast.makeText(context, "Not yet Implemented ", Toast.LENGTH_SHORT).show()

        }


    }



    private fun gettingViewModelReady(){
        val factory = HomeSellerViewModelFactory(HomeSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[HomeSellerViewModel::class.java]
    }
}