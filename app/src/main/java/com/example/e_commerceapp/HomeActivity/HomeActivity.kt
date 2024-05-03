package com.example.e_commerceapp.HomeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.HomeActivity.Favourites.view.FavsFragment
import com.example.e_commerceapp.HomeActivity.HomeScreen.view.HomeFragment
import com.example.e_commerceapp.HomeActivity.Settiings.view.SettingsFragment
import com.example.e_commerceapp.HomeActivity.ShopScreen.view.ShopFragment
import com.example.e_commerceapp.HomeActivity.cart.view.CartFragment
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var navigationView: NavigationView

    lateinit var navController: NavController
    lateinit var navHostFragment : NavHostFragment
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.bottomAppBar)

       // val navController = this.findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment


        navController = navHostFragment.navController
        binding.bottomnavigationbar.setupWithNavController(navController)



        }



    }









