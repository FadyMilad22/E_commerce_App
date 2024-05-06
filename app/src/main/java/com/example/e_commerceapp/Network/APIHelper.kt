package com.example.e_commerceapp.Network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIHelper {

//
//
//    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val request = chain.request()
//            val response = chain.proceed(request)
//
//            // Access the status code
//            val statusCode = response.code()
//            Log.i("Fad1234","The Status code is $statusCode")
//
//            // Optionally, handle the status code here
////            if (statusCode == 400) {
////                // Handle 400 Bad Request error
////            } else if (!response.isSuccessful) {
////                // Handle other error codes
////            }
//
//            response
//        }
//     .build()

    val gson: Gson = GsonBuilder().serializeNulls().create()
    val retrofit: Retrofit = Retrofit.Builder()
       // .baseUrl("https://metal-ants-think.loca.lt/")
       .baseUrl("https://e-commerce-backend-atmh.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create(gson)).build()


    //https://e-commerce-backend-atmh.onrender.com/items/categories/:category
    //    .client(okHttpClient)




}