package com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred


class SearchAdapter(val data:List<Item>,
                    val viewModel: ShopViewModel,
                    val token :String,
                    val context :Context,
                    val lifecycleOwner: LifecycleOwner,
                    private val onProductClick: (Item) -> Unit  ) :RecyclerView.Adapter<SearchAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a:Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
        holder.textViewTitle.text = data[position].Name
        holder.textViewPrice.text = "Price :${data[position].Price.toString()} $"
        holder.textViewQuantity.text = "Availability : ${data[position].Quantity.toString()}"
        val imgView:ImageView = holder.imageView
        Glide.with(holder.itemView.context)
            .load(data[position].URL)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadingsvg)
                    .error(R.drawable.broken_image))
            .into(imgView)
//        viewModel.insertMeal(data[position])
//        val meal = data[position]
//        holder.itemView.setOnClickListener {
//        onRecipeClick(meal)
//        }


        val Item = data[position]

        holder.itemView.setOnClickListener {
            onProductClick(Item)
        }



        holder.addbtn.setOnClickListener() {

            if (data[position].Quantity >0){
                val cartItem = CartItem(data[position].Item_ID!!, 1)

                viewModel.addItemToCart(cartItem,token)}
            else{
                Toast.makeText(context,"This Product isn't Available currently" , Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.succesfulAdding.observe(lifecycleOwner){
            if(it==true){
                Toast.makeText(context,"Added to Cart Successfully", Toast.LENGTH_SHORT).show()

                viewModel.returnFalse()

            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
    val textViewTitle: TextView = row.findViewById(R.id.productName_TextView)
        val addbtn: CardView  = row.findViewById(R.id.cardView)
      val imageView: ImageView = row.findViewById(R.id.productImage)
        val textViewQuantity: TextView = row.findViewById(R.id.productType_TextView)
        val textViewPrice: TextView = row.findViewById(R.id.price)


    }
}