package com.example.e_commerceapp.HomeActivity.Settiings.Repo



import com.example.e_commerceapp.Model.Address
import com.example.e_commerceapp.Model.AddressResponse
import com.example.e_commerceapp.Model.BalanceChargeResponse
import com.example.e_commerceapp.Model.CharingBalance
import com.example.e_commerceapp.Model.UserDataResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response

class SettingsRepoImpl(val remoteDataSource: RemoteDataSource,
                ): SettingsRepo {

override suspend fun getUserData(token: String): Response<UserDataResponse> {
    return remoteDataSource.getUserData("customer",token)
}

    override suspend fun addAddress(address: Address,token: String): Response<AddressResponse> {
        return remoteDataSource.addAddress(address,token)
    }

    override suspend fun chargeBalance(card : CharingBalance,token: String): Response<BalanceChargeResponse> {
        return remoteDataSource.chargeBalance(card,token)
    }




                }