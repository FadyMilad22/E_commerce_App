package com.example.e_commerceapp.HomeActivity.ShopScreen.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepoImpl
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModelFactory
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.view.ProductsDetailsSellerFragmentArgs
import com.example.e_commerceapp.databinding.FragmentProductDetailsCustomerBinding
import com.example.e_commerceapp.databinding.FragmentProductsDetailsSellerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.checkerframework.checker.initialization.qual.Initialized
import kotlin.math.log
import kotlin.properties.Delegates


class ProductDetailsCustomerFragment : Fragment() {



  private val args : ProductDetailsCustomerFragmentArgs by navArgs()

    private lateinit var newNumber :Number
    private lateinit var viewModel: ShopViewModel
    private lateinit var binding:FragmentProductDetailsCustomerBinding

    val isNumberInitialized: Boolean
        get() = ::newNumber.isInitialized

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductDetailsCustomerBinding.inflate(inflater,container,false)
        return binding.root }


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
            textView21.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    val newText = editable.toString().trim()
                    if (newText.isNotEmpty()) {
                        if (isValidNumber(newText)) {
                            newNumber = newText.toInt()
                            textView20.text = (newNumber as Int *(args.Price)).toString()
                        } else {
                            textView21.error = "Please Enter a Valid number"
                            newNumber= 0
                        } } }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // For the add to cart button
            button2.setOnClickListener() {
                if (!(textView21.text.toString().equals("0")) && isValidNumber(textView21.text.toString())) {
                    if (textView21.text.toString().toInt()<args.Quantity){
                        val cartItem = CartItem(args.ItemID, textView21.text.toString().toInt())
                        Log.i("Fady43", args.token)
                        Log.i("Fady43", textView21.text.toString())
                        viewModel.addItemToCart(cartItem, args.token)}
                    else{
                        Toast.makeText(context,"This Quantity isn't Available currently" ,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context,"Please enter a valid number" ,Toast.LENGTH_SHORT).show()
                }
            }

            viewModel.succesfulAdding.observe(viewLifecycleOwner){
                if(it==true){
                Toast.makeText(context,"Added to Cart Successfully",Toast.LENGTH_SHORT).show()
                viewModel.returnFalse()
//              view.findNavController().navigate(R.id.action_productDetailsCustomerFragment_to_cartFragment)
//              view.findNavController().popBackStack(view.findNavController().currentDestination!!.id,true)
                }
            }
        }

    }


    private fun gettingViewModelReady(){
        val shopFactory = ShopViewModelFactory(ShopRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,shopFactory)[ShopViewModel::class.java]
    }

    private fun isValidNumber(input: String): Boolean {
        // Check for empty string or spaces first for efficiency
        if (input.isEmpty() || input.isBlank()) {
            return false
        }

        val numberRegex = "^[0-9]+$".toRegex() // Only digits allowed
        return input.matches(numberRegex)
    }


}