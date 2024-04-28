package com.example.e_commerceapp.HomeActivity.HomeScreen.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepoImpl
import com.example.e_commerceapp.HomeActivity.HomeScreen.adaptor.HomeAdapter
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModel
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModelFactory
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


        viewModel.getExclusiveDeals()
        viewModel.getHotDeals()


        viewModel.hotPrices.observe(viewLifecycleOwner){ items->
            if(items != null){
                binding.progresBarAllMeals.visibility = View.GONE
                addElements(items,binding.recyclerViewExclusive)
                 }else{
                binding.progresBarAllMeals.visibility = View.VISIBLE
            }
        }



        viewModel.exclusiveDeals.observe(viewLifecycleOwner){ items->
            if(items != null){
                binding.progressBarRandomMeal.visibility = View.GONE
                addElements(items,binding.recyclerViewHot)
            }else{
                binding.progressBarRandomMeal.visibility= View.VISIBLE
            }
        }


        super.onViewCreated(view, savedInstanceState)

    }


    private fun addElements(data:List<Item>, recyclerView: RecyclerView){
        recyclerView.adapter = HomeAdapter(data, viewModel
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.HORIZONTAL, false)
    }





    private fun gettingViewModelReady(){
        val homeFactory = HomeViewModelFactory(HomeRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,homeFactory)[HomeViewModel::class.java]
    }

}