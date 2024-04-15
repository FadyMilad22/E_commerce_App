package com.example.e_commerceapp.Authentication.SignUp.view

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
import com.example.e_commerceapp.Authentication.SignUp.viewmodel.SignupViewModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentSignupBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

//import com.example.e_commerceapp.Authentication.SignUp.view.SignupFragmentBinding

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


       binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady(requireContext())
        viewModel.InitFirebase()

       binding.apply {
           button4.setOnClickListener {
               isValidData(inputEmail.text.toString(),inputUsername.text.toString(),
                   inputMobile.text.toString(),inputPassword.text.toString()) } }




viewModel.successfullRegister.observe(viewLifecycleOwner){

    if (it){
        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
        view.findNavController().navigate(R.id.action_signupFragment_to_homeActivity)
    }else {
        Toast.makeText(context, "NotDone", Toast.LENGTH_SHORT).show() }

}



    }








    private fun gettingViewModelReady(context: Context) {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
//        val loginViewModelFactory = LoginViewModelFactory(
//            LoginRepoImp(LocalDataSourceImpl(context)) )
//        loginViewModel =
//            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }




    private fun isValidData(email:String,name:String,phoneNumber:String,password: String){
        Log.d("Fady","Email:$email")
        Log.d("Fady","name:$name")
        Log.d("Fady","number:$phoneNumber")
        Log.d("Fady","pass:$password")
        if(isValidName(name)&& isValidPhoneNumber(phoneNumber)&& isValidPassword(password) &&isValidEmail(email)){
            viewModel.RegisterUserFirebase(email,password,name,phoneNumber)

        }
        if(!isValidName(name)) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Firstname").setMessage("In the first name, there must be 3 to 30 letters, either capital or small").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidPhoneNumber(phoneNumber)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid PhoneNumber").setMessage("In the Phone Number, there must be 11 digits").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidPassword(password)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Password").setMessage("A password must have between four and eight letters, both lowercase and uppercase letters, and contain special characters.").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidEmail(email)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Email").setMessage("Check that the email you entered is a valid email").setPositiveButton("Ok", null)
                .show()
        }
    }




    private fun isValidEmail(email :String):Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }
    private fun isValidName(name:String):Boolean{
        val nameRegex="^[A-Za-z]{3,30}\$"
        return name.matches(nameRegex.toRegex())
    }
    private fun isValidPassword(password:String):Boolean{
        val passwordRegex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,8}$"
        return password.matches(passwordRegex.toRegex())
    }
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneRegex = "^\\d{11}$"  // Ensures exactly 11 digits
        return phoneNumber.matches(phoneRegex.toRegex())
    }


}