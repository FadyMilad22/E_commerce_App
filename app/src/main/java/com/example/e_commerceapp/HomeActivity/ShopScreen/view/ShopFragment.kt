package com.example.e_commerceapp.HomeActivity.ShopScreen.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.R

class ShopFragment : Fragment() {

    companion object {
        fun newInstance() = ShopFragment()
    }

    private lateinit var viewModel: ShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}