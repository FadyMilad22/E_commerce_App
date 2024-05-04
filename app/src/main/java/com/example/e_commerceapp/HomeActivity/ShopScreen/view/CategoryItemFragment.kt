package com.example.e_commerceapp.HomeActivity.ShopScreen.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepoImpl
import com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor.SearchAdapter
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModelFactory
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.databinding.FragmentCategoryItemBinding


class CategoryItemFragment : Fragment() {


      private val args : CategoryItemFragmentArgs by navArgs()


    private lateinit var viewModel: ShopViewModel
    private lateinit var binding:FragmentCategoryItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryItemBinding.inflate(inflater,container,false)
        return binding.root  }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    gettingViewModelReady()


        viewModel.getthisCategoryItems(args.name)


        viewModel.categoryItems.observe(viewLifecycleOwner){

            if (it != null){

                addElements(it, binding.recyclerViewShop,args.token!!)

            }

        }


    }




    private fun addElements(data: List<Item>, recyclerView: RecyclerView, token:String){
        recyclerView.adapter = SearchAdapter(data, viewModel){ onProductClick(it,token) }

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2,
            RecyclerView.VERTICAL,false)
    }


    private fun gettingViewModelReady(){
        val shopFactory = ShopViewModelFactory(ShopRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,shopFactory)[ShopViewModel::class.java]
    }


    private fun onProductClick(clickedItem: Item, token:String) {
        Log.i("Fadyitem","true")
        Log.i("Fadyitem","${token}")
        val action = CategoryItemFragmentDirections.actionCategoryItemFragmentToProductDetailsCustomerFragment(Name = clickedItem.Name,
            Price = clickedItem.Price.toFloat(), Quantity = clickedItem.Quantity, ItemID = clickedItem.Item_ID!!,
            Description = clickedItem.Description!!, URL = clickedItem.URL!!, Category = clickedItem.categories!! , token = token) // token = args.token
        findNavController().navigate(action)
    }


}

