package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.ProductDetailModelClassItem
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R

class DisplayAdpter(var context: Context, var list: List<ProductDetailModelClassItem>?,
     var data: ((id: Int,position : Int) -> Unit)
) : RecyclerView.Adapter<DisplayAdpter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView = itemView.findViewById(R.id.id)
        var title: TextView = itemView.findViewById(R.id.title)
        var price: TextView = itemView.findViewById(R.id.price)
        var description: TextView = itemView.findViewById(R.id.description)
        var category: TextView = itemView.findViewById(R.id.category)
        var rating: TextView = itemView.findViewById(R.id.rating)
        var img: ImageView = itemView.findViewById(R.id.img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.display_item_file, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            data.invoke(list?.get(position)?.id!!,position)
        }

        holder.id.text = list?.get(position)?.id.toString()
        holder.title.text = list?.get(position)?.title.toString()
        holder.price.text = list?.get(position)?.price.toString()
        holder.description.text = list?.get(position)?.description.toString()
        holder.category.text = list?.get(position)?.category.toString()
        holder.rating.text = list?.get(position)?.rating.toString()

        Glide.with(holder.itemView.context).load(list?.get(position)?.image).into(holder.img)

    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}