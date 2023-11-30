package com.example.expensemanager.Adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.expensemanager.Model.DisplayModalClass
import com.example.expensemanager.R
import android.widget.AdapterView.OnItemSelectedListener as OnItemDelete

class DisplayAdapter(var context: Context, var Displaylist: ArrayList<DisplayModalClass>) :
    RecyclerView.Adapter<DisplayAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var cardViewColour: CardView = itemView.findViewById(R.id.cardViewColour)

        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var txttype: TextView = itemView.findViewById(R.id.txttype)
        var txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
        var txtNote: TextView = itemView.findViewById(R.id.txtNote)
        var txtCat: TextView = itemView.findViewById(R.id.txtCat)
        var txtMode: TextView = itemView.findViewById(R.id.txtMode)
        var imgDelete: ImageView = itemView.findViewById(R.id.imgDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.display_item_file, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Displaylist.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtDate.text = Displaylist[position].date.toString()
        holder.txtAmount.text = Displaylist[position].amount.toString()
        holder.txtNote.text = Displaylist[position].note.toString()
        holder.txttype.text = Displaylist[position].type.toString()
        holder.txtCat.text = Displaylist[position].category.toString()
        holder.txtMode.text = Displaylist[position].mode.toString()

//        holder.imgDelete.setOnClickListener {
//
//            OnItemDelete.invoke(Displaylist[position].id)
//        }


        if (holder.txttype.text.toString() == "Income") {
            holder.txtAmount.setTextColor(Color.BLUE)
            holder.txttype.setTextColor(Color.BLUE)
            Log.e("TAG", "onBindViewHolder: " + holder.txtAmount.text.toString())
        } else {
            holder.txttype.setTextColor(Color.RED)
            holder.txtAmount.setTextColor(Color.RED)
            Log.e("TAG", "onBindViewHolder: " + holder.txtAmount.text.toString())
        }

    }

    fun refresh(Displaylist: ArrayList<DisplayModalClass>) {
        this.Displaylist = ArrayList()
        this.Displaylist.addAll(Displaylist)
        notifyDataSetChanged()
    }
}