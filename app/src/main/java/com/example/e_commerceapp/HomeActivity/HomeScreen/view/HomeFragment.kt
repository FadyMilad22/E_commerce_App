package com.example.e_commerceapp.HomeActivity.HomeScreen.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepoImpl
import com.example.e_commerceapp.HomeActivity.HomeScreen.adaptor.HomeAdapter
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModel
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModelFactory
import com.example.e_commerceapp.HomeActivity.ShopScreen.view.ShopFragmentDirections
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val token: String? = requireActivity().intent.getStringExtra("token")


        if (token != null) {


            viewModel.setStringData(token)
            Log.i("Fady1", token)

        }

        viewModel.getExclusiveDeals()
        viewModel.getHotDeals()


        viewModel.hotPrices.observe(viewLifecycleOwner){ items->
            if(items != null){
                binding.progresBarAllMeals.visibility = View.GONE
                addElements(items,binding.recyclerViewExclusive,token!!)
                 }else{
                binding.progresBarAllMeals.visibility = View.VISIBLE
            }
        }



        viewModel.exclusiveDeals.observe(viewLifecycleOwner){ items->
            if(items != null){
                binding.progressBarRandomMeal.visibility = View.GONE
                addElements(items,binding.recyclerViewHot,token!!)
            }else{
                binding.progressBarRandomMeal.visibility= View.VISIBLE
            }
        }

//        val action = HomeFragmentDirections.actionHomeFragmentToShopFragment(token)
//        navController.navigate(action)




        super.onViewCreated(view, savedInstanceState)

    }


    private fun addElements(data:List<Item>, recyclerView: RecyclerView, token :String){
        recyclerView.adapter = HomeAdapter(data, viewModel
        ){ onProductClick(it,token)}

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.HORIZONTAL, false)



    }






    private fun gettingViewModelReady(){
        val homeFactory = HomeViewModelFactory(HomeRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,homeFactory)[HomeViewModel::class.java]
    }

    private fun onProductClick(clickedItem: Item ,token :String) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsCustomerFragment (Name = clickedItem.Name,
            Price = clickedItem.Price.toFloat(), Quantity = clickedItem.Quantity, ItemID = clickedItem.Item_ID!!,
            Description = clickedItem.Description!!, URL = clickedItem.URL!!, Category = clickedItem.categories!!,token = token ) // token = args.token
        findNavController().navigate(action)
    }

}