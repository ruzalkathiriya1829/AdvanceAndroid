package com.example.quetesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quetesapp.Model.displayModel
import com.example.quetesapp.R

class displayAdapter(
    var context: Context,
    var list: ArrayList<displayModel>,
    var quotesImage: Array<Int>,
    var onclick:((id:Int ,name:String,)->Unit)
    ): RecyclerView.Adapter<displayAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtText: TextView = itemView.findViewById(R.id.txtText)
        var img: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): displayAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_category_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: displayAdapter.MyViewHolder, position: Int) {

        holder.txtText.text = list[position].categoryName
       // holder.txtText.text = list[position].id.toString()

        holder.img.setImageResource(quotesImage[position])

        holder.img.setOnClickListener {
            onclick.invoke(list[position].id,list[position].categoryName)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
