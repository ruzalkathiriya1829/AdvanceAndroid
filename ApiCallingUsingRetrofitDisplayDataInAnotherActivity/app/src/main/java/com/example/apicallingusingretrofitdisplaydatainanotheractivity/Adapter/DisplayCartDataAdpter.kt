package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.CartDetailsModel
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.CartDetailsModelItem
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R

class DisplayCartDataAdpter(
    var context: Context, var cartList: List<CartDetailsModelItem>?
):RecyclerView.Adapter<DisplayCartDataAdpter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        var txtid : TextView = itemView.findViewById(R.id.txtid)
        var userId : TextView = itemView.findViewById(R.id.userid)
        var date : TextView = itemView.findViewById(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayCartDataAdpter.MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_cart_details_item_file,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtid.text = cartList?.get(position)?.id.toString()
        holder.userId.text = cartList?.get(position)?.userId.toString()
        holder.date.text = cartList?.get(position)?.date.toString()


    }

    override fun getItemCount(): Int {
        return cartList!!.size
    }


}