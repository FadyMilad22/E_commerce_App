package com.example.e_commerceapp.HomeActivity.History.adaptor


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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.HomeActivity.History.viewModel.HistoryViewModel
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.HomeActivity.cart.adaptor.CartAdapter
import com.example.e_commerceapp.HomeActivity.cart.adaptor.HistoryminiAdapter
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.History
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemX
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred



class HistoryMegaAdapter(val data:List<History>,
                         val viewModel: HistoryViewModel,
                         val token : String,
                         val lifecycleOwner: LifecycleOwner,
                         val context: Context) :RecyclerView.Adapter<HistoryMegaAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a: Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.historycard, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//

        val functionResult = extractDateParts(data[position].orderDate)
        holder.textViewTotal.text = data[position].orderPrice.toString() + " $"
        holder.textViewID.text = data[position].orderID.toString()
        holder.textViewDate.text = "${functionResult.third}/${functionResult.second}/${functionResult.first}"
        addElementsCart(data[position].items,holder.recyclerViewMini,token)


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textViewTotal: TextView = row.findViewById(R.id.textView37)
        val textViewDate: TextView = row.findViewById(R.id.textView36)
        val textViewID: TextView = row.findViewById(R.id.textView38)
        val recyclerViewMini: RecyclerView = row.findViewById(R.id.minirecy)



    }


    private fun addElementsCart(data: List<ItemX>, recyclerView: RecyclerView, token: String) {
        recyclerView.adapter =
            HistoryminiAdapter(data, viewModel, token, lifecycleOwner = lifecycleOwner) { Unit }

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


    }


    fun extractDateParts(dateStr: String?): Triple<Int?, Int?, Int?> {
        if (dateStr.isNullOrEmpty()) {
            return Triple(null, null, null)
        }

        val parts = dateStr.split("T")[0].split("-")
        if (parts.size != 3) {
            return Triple(null, null, null)
        }

        return try {
            Triple(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
        } catch (e: NumberFormatException) {
            Triple(null, null, null)
        }
    }



}

