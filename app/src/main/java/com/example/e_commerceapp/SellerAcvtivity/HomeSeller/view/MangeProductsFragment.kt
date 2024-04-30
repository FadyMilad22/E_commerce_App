package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor.SearchAdapter
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModelFactory
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.view.AddProductsSellerFragmentArgs
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.adaptor.SellerHomeAdapter
import com.example.e_commerceapp.databinding.FragmentHomeSellerBinding
import com.example.e_commerceapp.databinding.FragmentMangeProductsBinding

class MangeProductsFragment : Fragment() {

    private val args : MangeProductsFragmentArgs by navArgs()

    private lateinit var binding : FragmentMangeProductsBinding
    private lateinit var viewModel: HomeSellerViewModel

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
        gettingViewModelReady()


            viewModel.getItemsOfThisSeller(args.token!!)
        Log.i("Fady12",args.token!!)



        viewModel.itemsOfthisSeller.observe(viewLifecycleOwner){items->

            if (items != null) {
            Log.i("Fady12",items.toString())
               //   binding.progresBarAllMeals.visibility = View.GONE
                addElementsSearch(items, binding.recyclerView)
            } else {
                //   binding.progresBarAllMeals.visibility = View.VISIBLE
            }


        }

        binding.fab.setOnClickListener(){
            Log.i("Fady12","The token : ${args.token}")
            val action = MangeProductsFragmentDirections.actionMangeProductsFragmentToAddProductsSellerFragment(args.token!!)
            Navigation.findNavController(view).navigate(action)
            Log.i("Fady12","The After ?")

        }


    }





    private fun gettingViewModelReady(){
        val factory = HomeSellerViewModelFactory(HomeSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[HomeSellerViewModel::class.java]
    }


    private fun addElementsSearch(data: List<Item>, recyclerView: RecyclerView){
        recyclerView.adapter = SellerHomeAdapter(data, viewModel ){ clickedProduct -> onProductClick(clickedProduct) }

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2,
            RecyclerView.VERTICAL,false)
    }

    private fun onProductClick(clickedItem: Item) {
        val action = MangeProductsFragmentDirections.actionMangeProductsFragmentToProductsDetailsSellerFragment (Name = clickedItem.Name,
            Price = clickedItem.Price.toFloat(), Quantity = clickedItem.Quantity, ItemID = clickedItem.Item_ID!!,
            Description = clickedItem.Description, URL = clickedItem.URL, Category = clickedItem.categories, token = args.token)
        findNavController().navigate(action)
    }

    }



