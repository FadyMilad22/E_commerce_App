package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo.AddProductsSellerRepoImpl
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModel
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel.AddProductsSellerViewModelFactory
import com.example.e_commerceapp.databinding.FragmentEditProductBinding
import com.example.e_commerceapp.databinding.FragmentProductsDetailsSellerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditProductFragment : Fragment() {


    private val args : EditProductFragmentArgs by navArgs()

    private lateinit var binding : FragmentEditProductBinding

    private lateinit var viewModel: AddProductsSellerViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  FragmentEditProductBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()


        binding.apply {


            textViewTitleDetails.text = args.Name.toEditable()
            textView9.text =args.Description.toEditable()
            textViewCatagory.text = args.Category.toEditable()
            Glide.with(requireContext())
                .load(args.URL)
                .centerCrop()
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loadingsvg)
                        .error(R.drawable.broken_image))
                .into(imageView3)
            textView13.text = args.Price.toString().toEditable()
            textView15.text = args.Quantity.toString().toEditable()
            textView5.text = args.URL.toEditable()

        button3.setOnClickListener(){

            val text = textViewCatagory.text.toString()  // Ensure text is a String
            val categoryList = text.split(" ")

// Optional: Remove empty elements if needed
            val filteredList = categoryList.filter { it.isNotEmpty() }
            val item =AddItem(categories = filteredList, Description = textView9.text.toString(),
                Name =textViewTitleDetails.text.toString(), Price =textView13.text.toString().toDouble(),
                Quantity = textView15.text.toString().toInt(), URL = textView5.text.toString()  )
            viewModel.editProduct(item =item,itemID= args.ItemID, token = args.token!!)
        }


        }
        viewModel.successfullEdit.observe(viewLifecycleOwner){
            if(it == true) {
                binding.apply {
                    val action =
                        EditProductFragmentDirections.actionEditProductFragmentToProductsDetailsSellerFragment(
                            Name =textViewTitleDetails.text.toString(), Price =textView13.text.toString().toFloat(),
                            Quantity = textView15.text.toString().toInt(), URL = textView5.text.toString(),
                            ItemID = args.ItemID,
                            Description = textView9.text.toString(),
                            Category =  textViewCatagory.text.toString(),
                            token = args.token
                        )

                Navigation.findNavController(view).navigate(action)
            }
                Log.i("Fady4","It's here Edit")
                MaterialAlertDialogBuilder(requireContext()).setTitle("Product Edited Successfully")
            }
        }





    }




    private fun gettingViewModelReady(){
        val factory = AddProductsSellerViewModelFactory(AddProductsSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[AddProductsSellerViewModel::class.java]
    }
    fun String.toEditable(): Editable {
        return Editable.Factory.getInstance().newEditable(this)
    }

}