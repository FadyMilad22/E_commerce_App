package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.Item


import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo.AddProductsSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModel
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModelFactory
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view.MangeProductsFragmentDirections
import com.example.e_commerceapp.databinding.FragmentAddProductsSellerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddProductsSellerFragment : Fragment() {

    private val args :AddProductsSellerFragmentArgs by navArgs()

private lateinit var binding:FragmentAddProductsSellerBinding
    private lateinit var viewModel: AddProductsSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddProductsSellerBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()


        binding.apply {
            addbtn.setOnClickListener(){
                Log.i("Fady12","The Button is Clicked")
                if(isValidNumber(price.text.toString())&& isValidNumber(quantity.text.toString())&& !(catigories.text.toString().isEmpty())&& !(descripe.text.toString().isEmpty())&&!(imgUrl.text.toString().isEmpty())) {
                    val myList = catigories.text!!.split(",")
                    val item: AddItem = AddItem(
                        categories = myList,
                        Name = name.text.toString(),
                        Description = descripe.text.toString(),
                        Price = price.text.toString().toDouble(),
                        Quantity = quantity.text.toString().toInt(),
                        URL = imgUrl.text.toString()
                    )
                    viewModel.addProduct(item, args.token!!)

                }else{
                    MaterialAlertDialogBuilder(requireContext()).setTitle("Warning").setMessage("Check all fields are filled in a right way ").setPositiveButton("ok",null).show()
                }
            }
        }

    viewModel.successfullAdding.observe(viewLifecycleOwner){
        if(it == true){
            val action = AddProductsSellerFragmentDirections.actionAddProductsSellerFragmentToMangeProductsFragment(args.token!!)
            Navigation.findNavController(view).navigate(action)

            Log.i("Fady4","It's here")
           MaterialAlertDialogBuilder(requireContext()).setTitle("Product added Successfully").setPositiveButton("ok",null).show()
        }
    }

    }



    private fun gettingViewModelReady(){
        val factory = AddProductsSellerViewModelFactory(AddProductsSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[AddProductsSellerViewModel::class.java]
    }



    private fun isValidNumber(input: String): Boolean {
        // Check for empty string or spaces first for efficiency
        if (input.isEmpty() || input.isBlank()) {
            return false
        }

        val numberRegex = "^[0-9]+$".toRegex() // Only digits allowed
        return input.matches(numberRegex)
    }


}