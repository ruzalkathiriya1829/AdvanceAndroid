package com.example.getemployeedatafromapi.Adapter

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getemployeedatafromapi.Model.EmployeeDetailsResponse
import com.example.getemployeedatafromapi.Model.EmployeeDetailsResponseItem
import com.example.getemployeedatafromapi.R

class DisplayEmployeeAdpter(var context: Context, var list: List<EmployeeDetailsResponseItem?>?,
//    var data: ((id: Int, Position: Int) -> Unit)
) : RecyclerView.Adapter<DisplayEmployeeAdpter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var empid: TextView = itemView.findViewById(R.id.empid)
        var EmpName: TextView = itemView.findViewById(R.id.EmpName)
        var EmpSalary: TextView = itemView.findViewById(R.id.EmpSalary)
        var empage: TextView = itemView.findViewById(R.id.empage)
        var empImg: TextView = itemView.findViewById(R.id.empImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(context).inflate(R.layout.display_emp_details_item_file, parent, false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        holder.itemView.setOnClickListener {
//            data.invoke(list?.get(position)?.id!!, position)
//        }

        holder.empid.text = list?.get(position)?.id.toString()
        holder.EmpName.text = list?.get(position)?.employeeName.toString()
        holder.EmpSalary.text = list?.get(position)?.employeeSalary.toString()
        holder.empage.text = list?.get(position)?.employeeAge.toString()
        holder.empImg.text = list?.get(position)?.profileImage.toString()

    }


    override fun getItemCount(): Int {

        return list!!.size
    }

}