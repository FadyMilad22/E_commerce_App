package com.example.e_commerceapp.HomeActivity.History.Repo

import com.example.e_commerceapp.Model.AddItemToCartResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Cart
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response


class cartRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): cartRepo {

    override suspend fun getCart(token: String): Response<Cart> {
        return remoteDataSource.GetCart(token)
    }

    override suspend fun editquantity(
        cartItem: CartItem,
        token: String
    ): Response<AddItemToCartResponse> {
        return remoteDataSource.EditItemInCart(cartItem, token)

    }

    override suspend fun deleteitem(itemID : Int,token : String) : Response<AddItemToCartResponse> {
        return remoteDataSource.deleteItemFromCart(itemID, token)
    }


    override suspend fun confirmOrder(token: String): Response<AuthResponse> {
        return remoteDataSource.confirmOrder(token)
    }




}