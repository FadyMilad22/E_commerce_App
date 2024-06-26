package com.example.e_commerceapp.Authentication.Login.view

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.e_commerceapp.Authentication.Login.Repo.LoginRepoImpl
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModel
import com.example.e_commerceapp.Authentication.Login.viewmodel.LoginViewModelFactory
import com.example.e_commerceapp.HomeActivity.HomeActivity
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.SellerActivity
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
        gettingViewModelReady()



        viewModel.InitFirebase()
        viewModel.alreadyLoggedIn(requireContext())

        binding.textView.setOnClickListener(){
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

                if( (viewModel.user.value?.userType).equals("seller")){
                    //val action = LoginFragmentDirections.actionLoginFragmentToSellerActivity()
                    //Navigation.findNavController(view).navigate(action)
                    //view.findNavController().navigate(R.id.action_loginFragment_to_sellerActivity)

                    MaterialAlertDialogBuilder(requireContext()).setTitle("Logging you IN")
                    val intent = Intent(this.context, SellerActivity::class.java)
                    intent.putExtra("token", viewModel.user.value?.accessToken.toString()) //data is a string variable holding some value.
                    startActivity(intent)
                    requireActivity().finish()

                }
                else {
                    //Toast.makeText(context, "Welcome ${viewModel.user.value?.userType}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this.context, HomeActivity::class.java)
                    intent.putExtra(
                        "token",
                        viewModel.user.value?.accessToken.toString()

                    )
                    //data is a string variable holding some value.
                    Log.i("Fady","${viewModel.user.value?.userType}")
                    startActivity(intent)
                    requireActivity().finish()
                }



            }else if(it ==false){
              Toast.makeText(context, "This user doesn't Exist", Toast.LENGTH_SHORT).show()
            }

        }

        binding.textView4.setOnClickListener(){

            MaterialAlertDialogBuilder(requireContext()).setTitle("Not Yet !").setMessage("Wait for this feather in the upcoming version\nTill then Save your Password !").setPositiveButton("Ok", null).show()


        }


    }







    private fun gettingViewModelReady(){
        val factory = LoginViewModelFactory(LoginRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[LoginViewModel::class.java]
    }


    private fun isValidData(email:String,password: String){
        Log.d("Fady","Email:$email")
        Log.d("Fady","pass:$password")
        if(isValidPassword(password) &&isValidEmail(email)){
            viewModel.loginUserFirebase(email,password,requireContext())
        }
        if(!isValidPassword(password)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Password").setPositiveButton("Ok", null).show()
        }
        if(!isValidEmail(email)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Email").setPositiveButton("Ok", null).show()
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