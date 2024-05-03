package com.example.e_commerceapp.HomeActivity.cart.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginStart
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.HomeActivity.History.viewModel.HistoryViewModel
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemX
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred



class HistoryminiAdapter(val data:List<ItemX>,
                         val viewModel: HistoryViewModel,
                         val token : String,
                         val lifecycleOwner: LifecycleOwner,
                         private val onProductClick: (Item) -> Unit  ) :RecyclerView.Adapter<HistoryminiAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a:Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.order_products_items_rv, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//

        holder.textViewTitle.text = data[position].name
        holder.textViewPrice.text = ((data[position].price)*(data[position].quantity)).toString()
        holder.textViewQuantity.text =  data[position].quantity.toString()

        val imgView:ImageView = holder.imageView
        Glide.with(holder.itemView.context)
            .load(data[position].URL)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadingsvg)
                    .error(R.drawable.broken_image))
            .into(imgView)


        holder.buttonplus.visibility = View.GONE
        holder.buttonMins.visibility = View.GONE
        holder.buttonX.visibility = View.GONE

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



    }

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
        val card :CardView = row.findViewById(R.id.card)

    }
}


