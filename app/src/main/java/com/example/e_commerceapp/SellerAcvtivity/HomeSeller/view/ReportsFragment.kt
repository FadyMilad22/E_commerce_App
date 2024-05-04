package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.History.adaptor.HistoryMegaAdapter
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModelFactory
import com.example.e_commerceapp.Model.History
import com.example.e_commerceapp.Model.Report
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.adaptor.ReportsMegaAdapter
import com.example.e_commerceapp.databinding.FragmentReportsBinding


class ReportsFragment : Fragment() {

    private val args : ReportsFragmentArgs by navArgs()

    private lateinit var binding : FragmentReportsBinding
    private lateinit var viewModel: HomeSellerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentReportsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()
        viewModel.getReports(args.token!!)


        viewModel.priceList.observe(viewLifecycleOwner){

        if (it != null){

            addElementsCart(it ,binding.recyclerViewHistory,args.token!!)
        }

    }





    }





    private fun gettingViewModelReady(){
        val factory = HomeSellerViewModelFactory(HomeSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[HomeSellerViewModel::class.java]
    }


    private fun addElementsCart(data: List<Report>, recyclerView: RecyclerView, token:String){
        recyclerView.adapter = ReportsMegaAdapter(data, viewModel,token,  context = requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
    }


}