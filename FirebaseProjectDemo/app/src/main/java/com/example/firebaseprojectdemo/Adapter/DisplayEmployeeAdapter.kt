package com.example.firebaseprojectdemo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseprojectdemo.ModalClass.EmployeeModal
import com.example.firebaseprojectdemo.R

class DisplayEmployeeAdapter(
    var empList: ArrayList<EmployeeModal>,
    var EditClick: ((empId: String, empName: String, empPhone: String, empEmail: String, empAddress: String, empSalary: String) -> Unit),
    var DeleteClick: ((empId : String) -> Unit)
) : RecyclerView.Adapter<DisplayEmployeeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtEmpId: TextView = itemView.findViewById(R.id.txtEmpId)
        var txtEmpName: TextView = itemView.findViewById(R.id.txtEmpName)
        var txtEmpPhone: TextView = itemView.findViewById(R.id.txtEmpPhone)
        var txtEmpEmail: TextView = itemView.findViewById(R.id.txtEmpEmail)
        var txtEmpAddress: TextView = itemView.findViewById(R.id.txtEmpAddress)
        var txtEmpSalary: TextView = itemView.findViewById(R.id.txtEmpSalary)
        var imgEdit : ImageView = itemView.findViewById(R.id.imgEdit)
        var imgDelete : ImageView = itemView.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_data_item_file, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.imgEdit.setOnClickListener {

                EditClick.invoke(empList[position].empId, empList[position].empName,
                            empList[position].empPhone,empList[position].empEmail,
                            empList[position].empAddress, empList[position].empSalary)
        }

        holder.imgDelete.setOnClickListener {

                DeleteClick.invoke(empList[position].empId)

        }

        holder.txtEmpId.text = empList[position].empId.toString()
        holder.txtEmpName.text = empList[position].empName.toString()
        holder.txtEmpPhone.text = empList[position].empPhone.toString()
        holder.txtEmpEmail.text = empList[position].empEmail.toString()
        holder.txtEmpAddress.text = empList[position].empAddress.toString()
        holder.txtEmpSalary.text = empList[position].empSalary.toString()

    }
}

