package com.example.e_commerceapp.HomeActivity.Settiings.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.e_commerceapp.HomeActivity.Settiings.Repo.SettingsRepoImpl
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModel
import com.example.e_commerceapp.HomeActivity.Settiings.viewModel.SettingsViewModelFactory
import com.example.e_commerceapp.Model.CharingBalance
import com.example.e_commerceapp.Network.APIClient
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.view.MangeProductsFragmentArgs
import com.example.e_commerceapp.databinding.FragmentChargeBalanceBinding
import com.example.e_commerceapp.databinding.FragmentSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ChargeBalanceFragment : Fragment() {

    private val args : ChargeBalanceFragmentArgs by navArgs()

    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding : FragmentChargeBalanceBinding
            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                binding = FragmentChargeBalanceBinding.inflate(inflater,container,false)
                return  binding.root }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gettingViewModelReady()


        binding.apply {

            textView10.text = args.name
            textView30.text = args.currentBalance.toString()

            buttonlog.setOnClickListener() {

                isValidPayment(inputcardnumber.text.toString(),cvv.text.toString(),amount.text.toString(),expiryDate.text.toString(),args.token!!)

            }

        }


        viewModel.successfulCharing.observe(viewLifecycleOwner){

            if(it == true){
            binding.apply {
                textView30.text =
                    (args.currentBalance + binding.amount.text.toString().toInt()).toString()
                inputcardnumber.setText("")
                amount.setText("")
                cvv.setText("")
                expiryDate.setText("")

            }

            }else{
                Toast.makeText(context,"Payment isn't Successful please check your card Info",Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun gettingViewModelReady(){
        val factory = SettingsViewModelFactory(SettingsRepoImpl(APIClient))
        viewModel = ViewModelProvider(this,factory)[SettingsViewModel::class.java]
    }

    private fun isValidPayment(cardNumber: String,cvv: String,amount:String,date: String,token :String):Unit {

        if(isValidCreditCardNumber(cardNumber)&& isValidNumber(amount)&&isValidExpiryDate(date)&& isValidCVV(cvv)) {
            val parts = date.split("/")

            val card = CharingBalance(
                amount.toInt(),
                cardNumber,
                cvv.toInt(),
                parts[0].toInt(),
                parts[1].toInt()
            )

            viewModel.chargeBalance(card, token)



        }
        if(!isValidCreditCardNumber(cardNumber)) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Card Number").setMessage("Card Number should be only exact 16 Digits").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidNumber(amount)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Amount").setMessage("The Amount should contain only numbers").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidCVV(cvv)){
            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid CVV").setMessage("The CVV should only be 3 or 4 Digits only numbers").setPositiveButton("Ok", null)
                .show()
        }
        if(!isValidExpiryDate(date)){

            MaterialAlertDialogBuilder(requireContext()).setTitle("Invalid Expiry Date").setMessage("The Date should be on the Form MM/YY").setPositiveButton("Ok", null)
                .show()
        }


    }


    private fun isValidCreditCardNumber(cardNumber: String): Boolean {
        // Regex for credit card number (covers most common formats)
        val cardRegex = "^[0-9]{13,16}$"

        // Check if the card number matches the regex and has only digits
        return cardNumber.matches(cardRegex.toRegex()) && cardNumber.all { it.isDigit() }
    }


    private fun isValidExpiryDate(date: String): Boolean {
        // Regex for MM/YY format
        val dateRegex = "^(0[1-9]|1[0-2])[/](\\d{2})$"

        // Check if the date matches the regex
        return date.matches(dateRegex.toRegex())
    }

    private fun isValidCVV(cvv: String): Boolean {
        // Check if the CVV is numeric and has a length of 3 or 4 (depending on card type)
        Log.i("Fadycvv", cvv.length.toString())
        return (cvv.trim().length == 3 || cvv.trim().length == 4)

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