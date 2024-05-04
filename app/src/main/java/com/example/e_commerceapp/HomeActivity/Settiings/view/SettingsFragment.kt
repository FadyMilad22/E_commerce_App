package com.example.e_commerceapp.HomeActivity.Settiings.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.e_commerceapp.Authentication.AuthenticationActivity
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepoImpl
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModel
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModelFactory
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {



    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
     gettingViewModelReady()

        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val token: String? = requireActivity().intent.getStringExtra("token")


if (token != null){

    viewModel.getCustomerData(token)


}


        binding.apply {

                buttonlog.setOnClickListener(){
                    viewModel.InitFirebase()
                    viewModel.logout()
                     // Replace with your actual class name
                    val intent = Intent(requireActivity(), AuthenticationActivity::class.java )
                    startActivity(intent)
                    requireActivity().finish()


                }

viewModel.userD.observe(viewLifecycleOwner){


    textView25.text = "Current Balance = ${it.Balance}"
    textView10.text = "Hi ${it.Username}"

    button1.setOnClickListener(){

        view.findNavController().navigate(R.id.action_settingsFragment_to_aboutFragment)

    }

    button2.setOnClickListener(){

        val action = SettingsFragmentDirections.actionSettingsFragmentToAddAddressFragment(token,
            viewModel.userD.value?.addresses.toString(),viewModel.userD.value?.Username!!)
        Navigation.findNavController(view).navigate(action)
    }

    button3.setOnClickListener(){

        val action = SettingsFragmentDirections.actionSettingsFragmentToChargeBalanceFragment(token,viewModel.userD.value?.Balance!!,viewModel.userD.value?.Username!!)
        Navigation.findNavController(view).navigate(action)
    }

}




        }}





    private fun gettingViewModelReady(){
        val factory = SettingsViewModelFactory(SettingsRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[SettingsViewModel::class.java]
    }
}