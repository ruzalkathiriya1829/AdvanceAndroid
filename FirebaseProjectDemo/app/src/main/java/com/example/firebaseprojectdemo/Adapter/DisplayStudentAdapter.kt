package com.example.firebaseprojectdemo.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseprojectdemo.ModalClass.StudentModal
import com.example.firebaseprojectdemo.databinding.StudentDataItemFileBinding

class DisplayStudentAdapter(var StudList : ArrayList<StudentModal>,
    var ClickEdit : ((StudId : String,StudGRID : String,StudName : String, StudPhone : String, StudEmail : String, StudCourse : String, StudFees : String, rbMale : String, rbFemale : String, cbCooking : String, cbTravelling : String, cbSinging : String) -> Unit),
    var ClickDelete : ((StudId : String) -> Unit)
) : RecyclerView.Adapter<DisplayStudentAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding : StudentDataItemFileBinding) : RecyclerView.ViewHolder(binding.root) {

//        var txtStudGRID : TextView = itemView.findViewById(R.id.txtStudGRId)
//        var txtStudName : TextView = itemView.findViewById(R.id.txtEmpName)
//        var txtStudPhone: TextView = itemView.findViewById(R.id.txtStudPhone)
//        var txtStudEmail : TextView = itemView.findViewById(R.id.txtStudEmail)
//        var txtStudCourse : TextView = itemView.findViewById(R.id.txtStudCourse)
//        var txtStudFees : TextView = itemView.findViewById(R.id.txtStudFees)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_display_student_data,parent,false)
//        return MyViewHolder(view)

        val binding = StudentDataItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.imgEdit.setOnClickListener {
            ClickEdit.invoke(StudList[position].StudId,StudList[position].StudGRID,StudList[position].StudName,StudList[position].StudPhone
                    ,StudList[position].StudEmail,StudList[position].StudCourse,StudList[position].StudFees,StudList[position].rbMale
                    ,StudList[position].rbFemale,StudList[position].cbCooking,StudList[position].cbTraveling,StudList[position].cbSinging)
        }

        holder.binding.imgDelete.setOnClickListener {
            ClickDelete.invoke(StudList[position].StudId)
        }
        with(holder)
        {
            with(StudList[position])
            {
                binding.txtStudGRId.text = this.StudGRID
                binding.txtStudName.text = this.StudName
                binding.txtStudPhone.text = this.StudPhone
                binding.txtStudEmail.text = this.StudEmail
                binding.txtStudCourse.text = this.StudCourse
                binding.txtStudFees.text = this.StudFees
                binding.txtStudMaleFemale.text = this.rbMale
                binding.txtStudMaleFemale.text = this.rbFemale
                binding.txtStudHobby.text = this.cbCooking
                binding.txtStudHobby.text = this.cbTraveling
                binding.txtStudHobby.text = this.cbSinging
            }
        }
//        holder.txtStudGRID.text = StudList[position].StudGRID.toString()
//        holder.txtStudName.text = StudList[position].StudName.toString()
//        holder.txtStudPhone.text = StudList[position].StudPhone.toString()
//        holder.txtStudEmail.text = StudList[position].StudEmail.toString()
//        holder.txtStudCourse.text = StudList[position].StudCourse.toString()
//        holder.txtStudFees.text = StudList[position].StudFees.toString()

    }

    override fun getItemCount(): Int {

        return StudList.size
    }
}