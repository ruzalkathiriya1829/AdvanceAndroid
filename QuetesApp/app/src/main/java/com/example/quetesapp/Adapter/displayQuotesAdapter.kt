package com.example.quetesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quetesapp.Model.displayQuotesModel
import com.example.quetesapp.R

class displayQuotesAdapter(
    var context: Context,
    var quoteslist: ArrayList<displayQuotesModel>,
    var Edit: ((id: Int, Quetos: String) -> Unit),
    var OnCopy: ((id: Int, Quotes: String) -> Unit),
    var Like: ((LikeStatus: Int, Id: String) -> Unit),
    var Share: ((id: Int, Quetos: String) -> Unit),
    var click: ((id:Int,Quetos:String) -> Unit)
) : RecyclerView.Adapter<displayQuotesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtQuotes: TextView = itemView.findViewById(R.id.txtQuotes)
        var imgEdit: ImageView = itemView.findViewById(R.id.imgEdit)
        var imgCopy: ImageView = itemView.findViewById(R.id.imgCopy)
        var imgShare: ImageView = itemView.findViewById(R.id.imgShare)
        var imgHeart: ImageView = itemView.findViewById(R.id.imgHeart)
        var quoteslinear: LinearLayout = itemView.findViewById(R.id.quoteslinear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_quotes_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtQuotes.text = quoteslist[position].id.toString()
        holder.txtQuotes.text = quoteslist[position].quotes

        holder.quoteslinear.setOnClickListener {
            click.invoke(quoteslist[position].id,quoteslist[position].quotes)
        }


        //copy
        holder.imgCopy.setOnClickListener {
            OnCopy.invoke(quoteslist[position].id, quoteslist[position].quotes)
        }

        //share
        holder.imgShare.setOnClickListener {
            Share.invoke(quoteslist[position].id, quoteslist[position].quotes)
        }

        //edit
        holder.imgEdit.setOnClickListener {
            Edit.invoke(quoteslist[position].id, quoteslist[position].quotes)
        }

        //like

        if (quoteslist[position].status == 100) {
            holder.imgHeart.setImageResource(R.drawable.fillheart)
        } else {
            holder.imgHeart.setImageResource(R.drawable.heart)
        }


        holder.imgHeart.setOnClickListener {


            if (quoteslist[position].status == 100)
            {
                holder.imgHeart.setImageResource(R.drawable.heart)
                quoteslist[position].status = 0
                Like.invoke(quoteslist[position].status,quoteslist[position].id.toString())
            }
            else
            {
                holder.imgHeart.setImageResource(R.drawable.fillheart)
                quoteslist[position].status = 100
                Like.invoke(quoteslist[position].status,quoteslist[position].id.toString())
            }
        }

    }

    override fun getItemCount(): Int {
        return quoteslist.size
    }
} 