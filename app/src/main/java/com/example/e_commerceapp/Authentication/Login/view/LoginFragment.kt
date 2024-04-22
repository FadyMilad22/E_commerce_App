package com.example.e_commerceapp.Authentication.Login.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModel
import com.example.e_commerceapp.Authentication.SignUp.viewmodel.SignupViewModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : Fragment() {



    private lateinit var viewModel: LoginViewModel
    private lateinit var binding :FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady(requireContext())
        viewModel.InitFirebase()


        binding.textView6.setOnClickListener(){
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }


        binding.apply {
            button4.setOnClickListener {
                isValidData(inputEmail.text.toString(),inputPassword.text.toString())
            }
        }

        viewModel.successfullLogin.observe(viewLifecycleOwner){
            if (it== true){
              Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
            }else if(it ==false){
              Toast.makeText(context, "This user doesn't Exist", Toast.LENGTH_SHORT).show()
            }

        }


    }








    private fun gettingViewModelReady(context: Context) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        val loginViewModelFactory = LoginViewModelFactory(
//            LoginRepoImp(LocalDataSourceImpl(context)) )
//        loginViewModel =
//            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }


    private fun isValidData(email:String,password: String){
        Log.d("Fady","Email:$email")
        Log.d("Fady","pass:$password")
        if(isValidPassword(password) &&isValidEmail(email)){
            viewModel.loginUserFirebase(email,password)
        }
        if(!isValidPassword(password)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Password")
        }
        if(!isValidEmail(email)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Email")
        }
    }



    private fun isValidEmail(email :String):Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }
    private fun isValidPassword(password:String):Boolean{
        val passwordRegex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"
        return password.matches(passwordRegex.toRegex())
    }

}