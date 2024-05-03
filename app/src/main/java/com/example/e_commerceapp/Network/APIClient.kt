package com.example.e_commerceapp.Network


import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.AddItemToCartResponse
import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Cart
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.CategoriesResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.LoginRequest
import com.example.e_commerceapp.Model.Seller
import com.example.e_commerceapp.Model.TokenHolder
import com.example.e_commerceapp.Model.UserDataResponse
import com.example.e_commerceapp.Model.UserLoginResponse
import retrofit2.Response


object APIClient: RemoteDataSource {


    override suspend fun registerCustomer(user: Customer): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).createCustomer(user)
    }

    override suspend fun registerSeller(user: Seller): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).createSeller(user)
    }

    override suspend fun getItemsOfCategory(category: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemsInCategory(category)
    }

    override suspend fun getItemsByName(name: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemByName(name)
    }

    override suspend fun getCategories(): Response<CategoriesResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getCategories()
    }

    override suspend fun AddItem(item: AddItem, token: String): Response<AddingItemResponse> {
        return APIHelper.retrofit.create(APIService::class.java).addItem(item,"Bearer "+token)

    }

    override suspend fun deleteItem(itemID: Int, token: String): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).deleteItem(itemID, "Bearer "+token)

    }

    override suspend fun updateItem(
        itemID: Int,
        token: String,
        item: AddItem
    ): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).editItem(itemID, "Bearer "+token, item)

    }

    override suspend fun getItemsofthisSeller(token: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemsOfthisSeller("Bearer "+token)
    }

    override suspend fun loginUser(loginResquest : LoginRequest ): Response<UserLoginResponse> {
//        return APIHelper.retrofit.create(APIService::class.java).userLogin(uid)
        return APIHelper.retrofit.create(APIService::class.java).userLogin(loginResquest)
    }




    override suspend fun getUserData(userType :String,token: String): Response<UserDataResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getUserData(userType,"Bearer "+token)
    }


    override suspend fun addItemtoCart(item: CartItem,token: String): Response<AddItemToCartResponse> {
       return APIHelper.retrofit.create(APIService::class.java).addItemToCart(item,"Bearer "+token)
   //  return APIHelper.retrofit.create(APIService::class.java).addItemToCart(item,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiJDVVNUMDAxIiwiaWF0IjoxNzE0Mzk3MTc4LCJleHAiOjQ4NzAxNTcxNzh9.1E6Aprw13VdZngrpUUYn3YSycpdzmGQHVJLYxB4pHOg")

    }


    override suspend fun EditItemInCart(item: CartItem,token: String): Response<AddItemToCartResponse> {
      return APIHelper.retrofit.create(APIService::class.java).EditItemQuantityInCart(item,"Bearer "+token)

   //     return APIHelper.retrofit.create(APIService::class.java).EditItemQuantityInCart(item,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiJDVVNUMDAxIiwiaWF0IjoxNzE0Mzk3MTc4LCJleHAiOjQ4NzAxNTcxNzh9.1E6Aprw13VdZngrpUUYn3YSycpdzmGQHVJLYxB4pHOg")

    }

    override suspend fun deleteItemFromCart(itemID: Int,token: String): Response<AddItemToCartResponse> {
       return APIHelper.retrofit.create(APIService::class.java).deleteItemFromCart(itemID,"Bearer "+token)

        // return APIHelper.retrofit.create(APIService::class.java).deleteItemFromCart(itemID,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiJDVVNUMDAxIiwiaWF0IjoxNzE0Mzk3MTc4LCJleHAiOjQ4NzAxNTcxNzh9.1E6Aprw13VdZngrpUUYn3YSycpdzmGQHVJLYxB4pHOg")

    }

    override suspend fun GetCart(token: String): Response<Cart> {
        return APIHelper.retrofit.create(APIService::class.java).getCustomerCart("Bearer "+token)


      // return APIHelper.retrofit.create(APIService::class.java).getCustomerCart("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiJDVVNUMDAxIiwiaWF0IjoxNzE0Mzk3MTc4LCJleHAiOjQ4NzAxNTcxNzh9.1E6Aprw13VdZngrpUUYn3YSycpdzmGQHVJLYxB4pHOg")

    }

    override suspend fun DeletellCart(token: String): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).deleteAllCart("Bearer "+token)
    }



}