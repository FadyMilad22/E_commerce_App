package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentHomeSellerBinding
import com.example.e_commerceapp.databinding.FragmentMangeProductsBinding

class MangeProductsFragment : Fragment() {


    private lateinit var binding : FragmentMangeProductsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =  FragmentMangeProductsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    }
