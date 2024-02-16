package com.example.apicallingusingretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DisplayDataAdapter(var context: Context, var Displaylist: List<ResultsItem?>?,) :
    RecyclerView.Adapter<DisplayDataAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView = itemView.findViewById(R.id.id)
        var author: TextView = itemView.findViewById(R.id.author)
        var content: TextView = itemView.findViewById(R.id.content)
        var authorSlug: TextView = itemView.findViewById(R.id.authorSlug)
        var length: TextView = itemView.findViewById(R.id.length)
        var dateAdded: TextView = itemView.findViewById(R.id.dateAdded)
        var dateModified: TextView = itemView.findViewById(R.id.dateModified)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.display_data_item_file, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Displaylist!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = Displaylist?.get(position)?.id
        holder.author.text = Displaylist?.get(position)?.author
        holder.content.text =Displaylist?.get(position)?.content
        holder.authorSlug.text = Displaylist?.get(position)?.authorSlug
        holder.length.text = Displaylist?.get(position)?.length.toString()
        holder.dateAdded.text = Displaylist?.get(position)?.dateAdded
        holder.dateModified.text =Displaylist?.get(position)?.dateModified
    }
}