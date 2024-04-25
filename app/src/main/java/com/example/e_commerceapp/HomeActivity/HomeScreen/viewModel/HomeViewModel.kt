package com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepo
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val homeRepo : HomeRepo) : ViewModel() {


    private val _exclusiveDeals = MutableLiveData<List<Item>>()
    val exclusiveDeals: LiveData<List<Item>> =  _exclusiveDeals


    private val _hotPrices = MutableLiveData<List<Item>>()
    val hotPrices: LiveData<List<Item>> = _hotPrices







    fun getExclusiveDeals(){
        viewModelScope.launch {
         //   val response = homeRepo.getAllMealsFromAPI("Exclusive")
          //  if (response.isSuccessful){
           // _exclusiveDeals.value = response.body()?.items


                val items = listOf(
                    Item("smartphones", "An apple mobile which is nothing like apple", "", 1, "iPhone 9", 549.0, 1, "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"),
                    Item("smartphones", "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...", "", 2, "iPhone X", 899.0, 1, "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg"),
                    Item("smartphones", "Samsung's new variant which goes beyond Galaxy to the Universe", "", 3, "Samsung Universe 9", 1249.0, 1, "https://cdn.dummyjson.com/product-images/3/thumbnail.jpg"),
                    Item("smartphones", "OPPO F19 is officially announced on April 2021.", "", 4, "OPPOF19", 280.0, 1, "https://cdn.dummyjson.com/product-images/4/thumbnail.jpg"),
                    Item("smartphones", "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.", "", 5, "Huawei P30", 499.0, 1, "https://cdn.dummyjson.com/product-images/5/thumbnail.jpg"),
                    Item("laptops", "MacBook Pro 2021 with mini-LED display may launch between September, November", "", 6, "MacBook Pro", 1749.0, 1, "https://cdn.dummyjson.com/product-images/6/thumbnail.png"),
                    Item("laptops", "Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched", "", 7, "Samsung Galaxy Book", 1499.0, 1, "https://cdn.dummyjson.com/product-images/7/thumbnail.jpg"),
                    Item("laptops", "Style and speed. Stand out on HD video calls backed by Studio Mics. Capture ideas on the vibrant touchscreen.", "", 8, "Microsoft Surface Laptop 4", 1499.0, 1, "https://cdn.dummyjson.com/product-images/8/thumbnail.jpg"),
                    Item("laptops", "Infinix Inbook X1 Ci3 10th 8GB 256GB 14 Win10 Grey – 1 Year Warranty", "", 9, "Infinix Inbook X1", 649.0, 1, "https://cdn.dummyjson.com/product-images/8/thumbnail.jpg"),
                    Item("tablets", "iPad Air 4th generation (2020) coming with A14 Bionic chip and USB-C port", "", 10, "iPad Air", 599.0, 1, "https://cdn.dummyjson.com/product-images/9/thumbnail.jpg"),
                    Item("tablets", "Lenovo IdeaPad Duet 3 Chromebook includes an octa-core processor, 4GB of RAM, and 128GB of storage", "", 11, "Lenovo IdeaPad Duet", 249.0, 1, "https://cdn.dummyjson.com/product-images/10/thumbnail.jpg"),
                    Item("tablets", "The Microsoft Surface Go 3 is a small, affordable Windows tablet", "", 12, "Microsoft Surface Go", 399.0, 1, "https://cdn.dummyjson.com/product-images/11/thumbnail.jpg"),
                    Item("tablets", "Samsung Galaxy Tab S7 FE - Powerful processor, large display, S Pen support", "", 13, "Samsung Galaxy Tab S7", 649.0, 1, "https://cdn.dummyjson.com/product-images/12/thumbnail.jpg"),
                    Item("watches", "Apple Watch SE - Fitness Tracking, Large Display, Multiple Colors", "", 14, "Apple Watch SE", 279.0, 1, "https://cdn.dummyjson.com/product-images/13/thumbnail.jpg"),
                )

                _exclusiveDeals.value = items


            //   }
        }
    }

    fun getHotDeals(){
        viewModelScope.launch {
          //  val response = homeRepo.getAllMealsFromAPI("Deals")
          //  if (response.isSuccessful){

             //   _hotPrices.value = response.body()?.items
                val items = listOf(
                    Item("smartphones", "An apple mobile which is nothing like apple", "", 1, "iPhone 9", 549.0, 1, "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"),
                    Item("smartphones", "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...", "", 2, "iPhone X", 899.0, 1, "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg"),
                    Item("smartphones", "Samsung's new variant which goes beyond Galaxy to the Universe", "", 3, "Samsung Universe 9", 1249.0, 1, "https://cdn.dummyjson.com/product-images/3/thumbnail.jpg"),
                    Item("smartphones", "OPPO F19 is officially announced on April 2021.", "", 4, "OPPOF19", 280.0, 1, "https://cdn.dummyjson.com/product-images/4/thumbnail.jpg"),
                    Item("smartphones", "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.", "", 5, "Huawei P30", 499.0, 1, "https://cdn.dummyjson.com/product-images/5/thumbnail.jpg"),
                    Item("laptops", "MacBook Pro 2021 with mini-LED display may launch between September, November", "", 6, "MacBook Pro", 1749.0, 1, "https://cdn.dummyjson.com/product-images/6/thumbnail.png"),
                    Item("laptops", "Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched", "", 7, "Samsung Galaxy Book", 1499.0, 1, "https://cdn.dummyjson.com/product-images/7/thumbnail.jpg"),
                    Item("laptops", "Style and speed. Stand out on HD video calls backed by Studio Mics. Capture ideas on the vibrant touchscreen.", "", 8, "Microsoft Surface Laptop 4", 1499.0, 1, "https://cdn.dummyjson.com/product-images/8/thumbnail.jpg"),
                    Item("laptops", "Infinix Inbook X1 Ci3 10th 8GB 256GB 14 Win10 Grey – 1 Year Warranty", "", 9, "Infinix Inbook X1", 649.0, 1, "https://cdn.dummyjson.com/product-images/8/thumbnail.jpg"),
                    Item("tablets", "iPad Air 4th generation (2020) coming with A14 Bionic chip and USB-C port", "", 10, "iPad Air", 599.0, 1, "https://cdn.dummyjson.com/product-images/9/thumbnail.jpg"),
                    Item("tablets", "Lenovo IdeaPad Duet 3 Chromebook includes an octa-core processor, 4GB of RAM, and 128GB of storage", "", 11, "Lenovo IdeaPad Duet", 249.0, 1, "https://cdn.dummyjson.com/product-images/10/thumbnail.jpg"),
                    Item("tablets", "The Microsoft Surface Go 3 is a small, affordable Windows tablet", "", 12, "Microsoft Surface Go", 399.0, 1, "https://cdn.dummyjson.com/product-images/11/thumbnail.jpg"),
                    Item("tablets", "Samsung Galaxy Tab S7 FE - Powerful processor, large display, S Pen support", "", 13, "Samsung Galaxy Tab S7", 649.0, 1, "https://cdn.dummyjson.com/product-images/12/thumbnail.jpg"),
                    Item("watches", "Apple Watch SE - Fitness Tracking, Large Display, Multiple Colors", "", 14, "Apple Watch SE", 279.0, 1, "https://cdn.dummyjson.com/product-images/13/thumbnail.jpg"),
                )

                _hotPrices.value = items




            //  }
        }
    }


}