package com.example.expensemanager.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemanager.Model.ModalClass
import com.example.expensemanager.R

class CategoryAdapter(var incomeExpenseActivity: Context, var Categorylist: java.util.ArrayList<ModalClass>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return Categorylist.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val v: View = LayoutInflater.from(incomeExpenseActivity).inflate(R.layout.item_file,null)
        val Category = v.findViewById<TextView>(R.id.txtItemCategories)
        Category.text = Categorylist[position].Category
        return v

    }

    fun refresh(list: ArrayList<ModalClass>) {

    }

    fun setDropDownViewResource(simpleSpinnerDropdownItem: Int) {

    }
}