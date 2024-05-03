package com.example.e_commerceapp.HomeActivity.History.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.History.Repo.HistoryRepoImpl
import com.example.e_commerceapp.HomeActivity.History.adaptor.HistoryMegaAdapter
import com.example.e_commerceapp.HomeActivity.History.viewModel.HistoryViewModel
import com.example.e_commerceapp.HomeActivity.History.viewModel.HistoryViewModelFactory
import com.example.e_commerceapp.HomeActivity.cart.adaptor.CartAdapter
import com.example.e_commerceapp.Model.History
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {


    private lateinit var binding: FragmentHistoryBinding

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()

        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token: String? = requireActivity().intent.getStringExtra("token")
        if (token!= null){
            viewModel.getHistory(token)
        }

        viewModel.histortList.observe(viewLifecycleOwner){

            if (it != null){

                addElementsCart(it ,binding.recyclerViewHistory,token!!)
            }

        }


    }







    private fun gettingViewModelReady(){
        val factory = HistoryViewModelFactory(HistoryRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[HistoryViewModel::class.java]
    }
    private fun addElementsCart(data: List<History>, recyclerView: RecyclerView, token:String){
        recyclerView.adapter = HistoryMegaAdapter(data, viewModel,token, lifecycleOwner = viewLifecycleOwner, context = requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
    }

}