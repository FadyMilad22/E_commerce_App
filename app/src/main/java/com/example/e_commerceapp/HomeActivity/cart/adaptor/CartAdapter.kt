package com.example.e_commerceapp.HomeActivity.cart.adaptor


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred



class CartAdapter(val data:List<Item>,
                  val viewModel: CartViewModel,
                 val token : String,
                  val lifecycleOwner: LifecycleOwner,
                  private val onProductClick: (Item) -> Unit  ) :RecyclerView.Adapter<CartAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a:Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.order_products_items_rv, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//

        holder.textViewTitle.text = data[position].Name
        holder.textViewPrice.text = ((data[position].Price)*(data[position].Quantity)).toString()
        holder.textViewQuantity.text =  data[position].Quantity.toString()

        val imgView:ImageView = holder.imageView
        Glide.with(holder.itemView.context)
            .load(data[position].URL)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadingsvg)
                    .error(R.drawable.broken_image))
            .into(imgView)


        holder.buttonMins.setOnClickListener(){
            Log.i("Fady-","Minus button")
             val cartItem = CartItem(data[position].Item_ID!!, Quantity = (data[position].Quantity-1))
            viewModel.editquantity(cartItem,token)

            it.isEnabled = false
            holder.buttonplus.isEnabled = false


        }
        holder.buttonplus.setOnClickListener(){

            Log.i("Fady+","blus button")
            Log.i("Fady+","item ID ${data[position].Item_ID!!}")
            Log.i("Fady+","Quantitiy ${data[position].Quantity+1}")
            val cartItem = CartItem(data[position].Item_ID!!, Quantity = (data[position].Quantity+1))
            viewModel.editquantity(cartItem,token)

            it.isEnabled = false
            holder.buttonMins.isEnabled = false



        }





        holder.buttonX.setOnClickListener(){


            viewModel.deleteItem(data[position].Item_ID!!,token.toString())
            Log.i("FadyDelete",token)
            Log.i("FadyDelete",data[position].Item_ID!!.toString())



        }


//        viewModel.insertMeal(data[position])
//        val meal = data[position]
//        holder.itemView.setOnClickListener {
//        onRecipeClick(meal)
//        }

//
//        val Item = data[position]
//
//        holder.itemView.setOnClickListener {
//            onProductClick(Item)
//        }



        viewModel.successfulEdit.observe(lifecycleOwner){

            if(it == true){
            holder.buttonMins.isEnabled = true
            holder.buttonplus.isEnabled = true}

        } }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textViewTitle: TextView = row.findViewById(R.id.textView23)
        val imageView: ImageView = row.findViewById(R.id.avatar)
        val textViewQuantity: TextView = row.findViewById(R.id.textView22)
        val textViewPrice: TextView = row.findViewById(R.id.totalprice)
        val buttonMins : Button =row.findViewById(R.id.button_1)
        val buttonplus : Button =row.findViewById(R.id.button_2)
        val buttonX : ImageView =row.findViewById(R.id.button_x)

    }
}


