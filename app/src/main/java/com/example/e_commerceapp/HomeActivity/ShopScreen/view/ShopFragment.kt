package com.example.e_commerceapp.HomeActivity.ShopScreen.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModelFactory

import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepoImpl
import com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor.SearchAdapter
import com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor.ShopAdapter
import com.example.e_commerceapp.Model.Category
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentShopBinding


class ShopFragment : Fragment() {


 //   private val args : ShopFragmentArgs by navArgs()

    private lateinit var viewModel: ShopViewModel
private lateinit var binding:FragmentShopBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()

        binding = FragmentShopBinding.inflate(inflater,container,false)
        return binding.root }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token: String? = requireActivity().intent.getStringExtra("token")
        viewModel.getCategories()


        binding.button.setOnClickListener(){
            if (binding.searchView.visibility.equals(View.GONE)){

                binding.searchView.visibility = View.VISIBLE
                binding.button.setImageResource(android.R.color.transparent)
                binding.button.setBackgroundResource(R.drawable.catigo)

            }else{
                viewModel.getCategories()
                viewModel.categories.observe(viewLifecycleOwner) { categories ->
                    if (categories != null) {
                        //     binding.progresBarAllMeals.visibility = View.GONE
                            addElements(categories, binding.recyclerViewShop, token!!)
                        binding.searchView.visibility = View.GONE
                        binding.button.setImageResource(com.bumptech.glide.R.drawable.abc_ic_search_api_material)
                        binding.button.setBackgroundResource(android.R.color.transparent)
                    } else { //   binding.progresBarAllMeals.visibility = View.VISIBLE
                    }
                }
            }



}



        viewModel.searchItems.observe(viewLifecycleOwner) { items ->
            if (items != null) {
                //     binding.progresBarAllMeals.visibility = View.GONE
                addElementsSearch(items, binding.recyclerViewShop, token!!)
            } else {
                //   binding.progresBarAllMeals.visibility = View.VISIBLE
            }
        }




        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                handleSearchQuery(p0!!)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                handleSearchQuery(p0!!)
                return true
            }


        })


//        binding.searchView.setOnCloseListener {
//
//            addElements(viewModel.categories.value!!, binding.recyclerViewShop)
//            false
//        }


    }


    private fun addElements(data:List<Category>, recyclerView: RecyclerView, token: String){
        recyclerView.adapter = ShopAdapter(data, viewModel){ onCategoryClick(it, token = token)}

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
    }

    private fun addElementsSearch(data: List<Item>, recyclerView: RecyclerView ,token:String){
        recyclerView.adapter = SearchAdapter(data, viewModel,token,requireContext(),viewLifecycleOwner
        ) { onProductClick(it, token )}

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
    }





    private fun gettingViewModelReady(){
        val shopFactory = ShopViewModelFactory(ShopRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,shopFactory)[ShopViewModel::class.java]
    }


    private fun handleSearchQuery(query: String) {
        if(query != "" && query != " " && query.isNotEmpty()) {viewModel.getSearchResult(query) }
//   else
//        viewModel.searchItems.value?.let { addElementsSearch(it,binding.recyclerViewShop) }
    }

    private fun onProductClick(clickedItem: Item,token:String) {
        Log.i("Fadyitem","true")
        Log.i("Fadyitem","${token}")
        val action = ShopFragmentDirections.actionShopFragmentToProductDetailsCustomerFragment (Name = clickedItem.Name,
            Price = clickedItem.Price.toFloat(), Quantity = clickedItem.Quantity, ItemID = clickedItem.Item_ID!!,
            Description = clickedItem.Description!!, URL = clickedItem.URL!!, Category = clickedItem.categories!! , token = token) // token = args.token
        findNavController().navigate(action)
    }


    private fun onCategoryClick(clickedCategory: Category,token:String) {
        Log.i("Fadyitem","true")
        Log.i("Fadyitem","${token}")
        val action = ShopFragmentDirections.actionShopFragmentToCategoryItemFragment(clickedCategory.Name,token = token) // token = args.token
        findNavController().navigate(action)
    }
}



