package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.e_commerceapp.Authentication.AuthenticationActivity
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepoImpl
import com.example.e_commerceapp.databinding.FragmentHomeSellerBinding

class HomeSellerFragment : Fragment() {



    private lateinit var viewModel: HomeSellerViewModel
private lateinit var binding : FragmentHomeSellerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()





        binding =  FragmentHomeSellerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val token: String? = requireActivity().intent.getStringExtra("token")


        if (token != null) {


            viewModel.setStringData(token)
         Log.i("Fady1", token)
            viewModel.getUserData(token)
        }

//        val viewModelShared: SharedViewModel by lazy {
//            ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
//        }
//        viewModelShared.token.observe(viewLifecycleOwner) { data ->
//            // Now you have your string data from the Activity
//
//        }


        binding.button1.setOnClickListener(){

            Toast.makeText(context, "Not yet Implemented ", Toast.LENGTH_SHORT).show()
        }



// Observe the string data


        binding.button2.setOnClickListener(){
            val action = HomeSellerFragmentDirections.actionHomeSellerFragmentToMangeProductsFragment(viewModel.token.value)
            Navigation.findNavController(view).navigate(action)


        }

        binding.button3.setOnClickListener() {



            binding.textView17.text = "Your Balance is : ${viewModel.userD.value?.Balance ?: "Loading"} $"
            // balance  Toast.makeText(context, "Not yet Implemented ", Toast.LENGTH_SHORT).show()
            binding.textView17.visibility = View.VISIBLE



        }

binding.buttonlog.setOnClickListener(){

        viewModel.InitFirebase()
        viewModel.logout()
    val intent = Intent(requireActivity(), AuthenticationActivity::class.java )
    startActivity(intent)
    requireActivity().finish()


}



    }



    private fun gettingViewModelReady(){
        val factory = HomeSellerViewModelFactory(HomeSellerRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[HomeSellerViewModel::class.java]
    }



}