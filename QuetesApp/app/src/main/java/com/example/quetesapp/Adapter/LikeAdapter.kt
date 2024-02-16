package com.example.quetesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quetesapp.Activity.LikeQuotesActivity
import com.example.quetesapp.Model.LikeQuoteModel
import com.example.quetesapp.R

class LikeAdapter(
    var likeAdapter: LikeQuotesActivity,
    var favouritelist: ArrayList<LikeQuoteModel>,
    var like: (Int, Int) -> Unit,
    var Edit: ((id: Int, Quetos: String) -> Unit),
    var OnCopy: ((id: Int, Quotes: String) -> Unit),
    var Share: ((id: Int, Quetos: String) -> Unit)
) : RecyclerView.Adapter<LikeAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtQuotes: TextView = itemView.findViewById(R.id.txtQuotes)
        var imgHeart: ImageView = itemView.findViewById(R.id.imgHeart)
        var imgEdit: ImageView = itemView.findViewById(R.id.imgEdit)
        var imgCopy: ImageView = itemView.findViewById(R.id.imgCopy)
        var imgShare: ImageView = itemView.findViewById(R.id.imgShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAdapter.MyViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.display_quotes_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeAdapter.MyViewHolder, position: Int) {


        holder.txtQuotes.text = favouritelist[position].Quotes


        //copy
        holder.imgCopy.setOnClickListener {
            OnCopy.invoke(favouritelist[position].id, favouritelist[position].Quotes)
        }

        //share
        holder.imgShare.setOnClickListener {
            Share.invoke(favouritelist[position].id, favouritelist[position].Quotes)
        }

        //edit
        holder.imgEdit.setOnClickListener {
            Edit.invoke(favouritelist[position].id, favouritelist[position].Quotes)
        }


        //like
        holder.imgHeart.setImageResource(R.drawable.fillheart)

        //like
        holder.imgHeart.setOnClickListener {

            like.invoke(0, favouritelist[position].id)
            favouritelist[position].status = 0

            //click button and set unlike
            deleteItem(position)  //create function and set position
        }


    }

        override fun getItemCount(): Int {

            return favouritelist.size
        }

    fun updateList(list: ArrayList<LikeQuoteModel>) {
        this.favouritelist = list
        notifyDataSetChanged()

    }

    fun deleteItem(position: Int) {
        favouritelist.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favouritelist.size)
    }

}