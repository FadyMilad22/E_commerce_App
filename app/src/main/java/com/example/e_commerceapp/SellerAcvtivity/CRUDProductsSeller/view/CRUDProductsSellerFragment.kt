package com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.Repo.CRUDProductsSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.viewModel.CRUDProductsSellerViewModel
import com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.viewModel.CRUDProductsSellerViewModelFactory

class CRUDProductsSellerFragment : Fragment() {



    private lateinit var viewModel: CRUDProductsSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()
        return inflater.inflate(R.layout.fragment_c_r_u_d_products_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    private fun gettingViewModelReady(){
        val factory = CRUDProductsSellerViewModelFactory(CRUDProductsSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[CRUDProductsSellerViewModel::class.java]
    }
}