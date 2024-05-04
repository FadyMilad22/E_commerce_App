package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.adaptor


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.HomeActivity.History.viewModel.HistoryViewModel
import com.example.e_commerceapp.HomeActivity.cart.adaptor.HistoryminiAdapter
import com.example.e_commerceapp.HomeActivity.cart.viewModel.HomeSellerViewModel
import com.example.e_commerceapp.Model.History
import com.example.e_commerceapp.Model.ItemX
import com.example.e_commerceapp.Model.Price
import com.example.e_commerceapp.Model.Report
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred



class ReportsMegaAdapter(val data:List<Report>,
                         val viewModel: HomeSellerViewModel,
                         val token : String,
                         val context: Context) :RecyclerView.Adapter<ReportsMegaAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a: Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.reportcard, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//

    //    val functionResult = extractDateParts(data[position].orderDate)
        holder.textViewProfit.text = data[position].TotalProfit.toString() + " $"
        holder.textViewName.text = data[position].Name
        holder.textViewQuantity.text = data[position].TotalQuantity.toString()
        addElementsCart(data[position].prices,holder.recyclerViewMini,token)


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textViewQuantity: TextView = row.findViewById(R.id.textView37)
        val textViewName: TextView = row.findViewById(R.id.textView36)
        val textViewProfit: TextView = row.findViewById(R.id.textView38)
        val recyclerViewMini: RecyclerView = row.findViewById(R.id.minirecy)



    }


    private fun addElementsCart(data: List<Price>, recyclerView: RecyclerView, url : String, ) {
        recyclerView.adapter =
           ReportsminiAdapter(data, viewModel, token, url) { Unit }

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


    }






}

