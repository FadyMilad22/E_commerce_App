package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo.AddProductsSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModel
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModelFactory
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view.MangeProductsFragmentArgs
import com.example.e_commerceapp.databinding.FragmentMangeProductsBinding
import com.example.e_commerceapp.databinding.FragmentProductsDetailsSellerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ProductsDetailsSellerFragment : Fragment() {

    private val args : ProductsDetailsSellerFragmentArgs by navArgs()

    private lateinit var binding : FragmentProductsDetailsSellerBinding
    private lateinit var viewModel: AddProductsSellerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProductsDetailsSellerBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()

binding.apply {
    textViewTitleDetails.text = args.Name
    textView9.text =args.Description
    textViewCatagory.text = args.Category
    Glide.with(requireContext())
        .load(args.URL)
        .centerCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loadingsvg)
                .error(R.drawable.broken_image))
        .into(imageView3)
    textView13.text = args.Price.toString()
    textView15.text = args.Quantity.toString()

button2.setOnClickListener(){

 viewModel.deleteProduct(args.ItemID,(args.token)!!)



}





}

        viewModel.successfullDelete.observe(viewLifecycleOwner){
            if(it == true){
                val action = ProductsDetailsSellerFragmentDirections.actionProductsDetailsSellerFragmentToMangeProductsFragment(args.token!!)
                Navigation.findNavController(view).navigate(action)

                Log.i("Fady4","It's here Delete")
                MaterialAlertDialogBuilder(requireContext()).setTitle("Product Deleted Successfully")
            }
        }



    }




    private fun gettingViewModelReady(){
        val factory = AddProductsSellerViewModelFactory(AddProductsSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[AddProductsSellerViewModel::class.java]
    }

}