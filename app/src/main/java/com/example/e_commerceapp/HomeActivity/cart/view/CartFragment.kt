package com.example.e_commerceapp.HomeActivity.cart.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.HomeActivity.Favourites.Repo.cartRepoImpl

import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R

class CartFragment : Fragment() {



    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    private fun gettingViewModelReady(){
        val factory = CartViewModelFactory(cartRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[CartViewModel::class.java]
    }
}