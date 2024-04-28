package com.example.e_commerceapp.HomeActivity.ShopScreen.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel.HomeViewModel
import com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel.ShopViewModel
import com.example.e_commerceapp.Model.Category
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.R


import kotlinx.coroutines.Deferred


class ShopAdapter(val data:List<Category>,
                  val viewModel: ShopViewModel,
          ) :RecyclerView.Adapter<ShopAdapter.MyViewHolder>() { //        private val onRecipeClick: (Item) -> Unit
    lateinit var a:Deferred<Unit>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.categorycard, parent, false)

        return MyViewHolder(row)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
        holder.textViewTitle.text = data[position].name


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
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
    val textViewTitle: TextView = row.findViewById(R.id.category_Name_TextView)
      val imageView: ImageView = row.findViewById(R.id.categoryImage)




    }
}