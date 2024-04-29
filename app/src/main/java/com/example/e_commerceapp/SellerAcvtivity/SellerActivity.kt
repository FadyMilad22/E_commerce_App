package com.example.e_commerceapp.SellerAcvtivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepoImpl

class SellerActivity : AppCompatActivity() {

//    private val args :SellerActivityArgs by navArgs()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)

    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

//        val sharedViewModel: SharedViewModel by lazy {
//            ViewModelProvider(this).get(SharedViewModel::class.java)
//        }
//        sharedViewModel.setToken(args.token)


        return super.onCreateView(parent, name, context, attrs)
    }



}
//class SharedViewModel : ViewModel() {
//    private val _token = MutableLiveData<String?>()
//    val token:LiveData<String?> = _token
//
//    fun setToken(data: String?) {
//        _token.value = data
//    }
//
//    fun getToken(): LiveData<String?> {
//        return token
//    }
//}