package com.example.e_commerceapp.HomeActivity.cart.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.History.Repo.cartRepoImpl
import com.example.e_commerceapp.HomeActivity.cart.adaptor.CartAdapter

import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModelFactory
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {



    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()
        val token: String? = requireActivity().intent.getStringExtra("token")
        if (token!= null){
        viewModel.getCart(token)}
        viewModel.cart.observe(viewLifecycleOwner){
            if (it != null){
                addElementsCart(it.items,binding.recyclerViewShop,token!!)
                binding.textView27.text = it.order.Total_payment.toString()
            }

        }

binding.button2.setOnClickListener(){

if(viewModel.cart.value?.order?.Total_payment != null){
    if (viewModel.cart.value?.order?.Total_payment!! > 0 ){
    viewModel.confirmOrder(token!!,requireContext())}}


    else{
    Toast.makeText(context,"First add at least one product to the cart",Toast.LENGTH_SHORT).show()

    }

}

        viewModel.successfulOrder.observe(viewLifecycleOwner){
            if(it == true ){



                addElementsCart(emptyList(),binding.recyclerViewShop,token!!)
                binding.textView27.text = "0"



            }else if ( it == false){
                Toast.makeText(context,"Not Confirmed Check your Balance",Toast.LENGTH_SHORT).show()

            }

        }



    }



    private fun gettingViewModelReady(){
        val factory = CartViewModelFactory(cartRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[CartViewModel::class.java]
    }

    private fun addElementsCart(data: List<Item>, recyclerView: RecyclerView, token:String){
        recyclerView.adapter = CartAdapter(data, viewModel,token, lifecycleOwner = viewLifecycleOwner){Unit }

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
    }


}