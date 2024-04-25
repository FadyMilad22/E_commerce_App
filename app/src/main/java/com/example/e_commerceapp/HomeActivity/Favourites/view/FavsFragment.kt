package com.example.e_commerceapp.HomeActivity.Favourites.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.HomeActivity.Favourites.Repo.FavRepoImpl
import com.example.e_commerceapp.HomeActivity.Favourites.viewModel.FavViewModelFactory
import com.example.e_commerceapp.HomeActivity.Favourites.viewModel.FavsViewModel
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R

class FavsFragment : Fragment() {

    companion object {
        fun newInstance() = FavsFragment()
    }

    private lateinit var viewModel: FavsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gettingViewModelReady()
        return inflater.inflate(R.layout.fragment_favs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




    private fun gettingViewModelReady(){
        val factory = FavViewModelFactory(FavRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[FavsViewModel::class.java]
    }
}