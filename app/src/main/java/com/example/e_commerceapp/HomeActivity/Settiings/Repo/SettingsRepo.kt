package com.example.e_commerceapp.HomeActivity.Settiings.Repo

import com.example.e_commerceapp.Model.Address
import com.example.e_commerceapp.Model.AddressResponse
import com.example.e_commerceapp.Model.BalanceChargeResponse
import com.example.e_commerceapp.Model.CharingBalance
import com.example.e_commerceapp.Model.UserDataResponse
import retrofit2.Response


interface SettingsRepo {

    suspend fun getUserData(token: String): Response<UserDataResponse>
    suspend fun addAddress(address: Address, token: String): Response<AddressResponse>
    suspend fun chargeBalance(card : CharingBalance, token: String): Response<BalanceChargeResponse>

}