package com.example.firebaseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(
    var empList: ArrayList<Employee>,
    var edtClick: ((String, String, String, String) -> Unit),
    var deleteClick: ((String) -> Unit)
) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var txtname: TextView = v.findViewById(R.id.txtName)
        var edt: TextView = v.findViewById(R.id.edt)
        var delete: TextView = v.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.employe_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtname.text = empList[position].name

        holder.edt.setOnClickListener {
            edtClick.invoke(
                empList[position].empId,
                empList[position].name,
                empList[position].Phone,
                empList[position].address
            )
        }

        holder.delete.setOnClickListener {
            deleteClick.invoke(empList[position].empId)
        }

    }
}