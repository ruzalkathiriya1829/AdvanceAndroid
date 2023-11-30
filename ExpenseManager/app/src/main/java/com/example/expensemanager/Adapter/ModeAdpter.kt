package com.example.expensemanager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemanager.Model.ModeModalclass
import com.example.expensemanager.R
import java.util.ArrayList

class ModeAdpter (var incomeExpenseActivity: Context, var Modelist: ArrayList<ModeModalclass>)
    : BaseAdapter() {
    override fun getCount(): Int {
            return Modelist.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v: View = LayoutInflater.from(incomeExpenseActivity).inflate(R.layout.mode_item_file, null)
        val Mode  = v.findViewById<TextView>(R.id.txtMode)
        Mode.text = Modelist[position].name
        return v
    }
}