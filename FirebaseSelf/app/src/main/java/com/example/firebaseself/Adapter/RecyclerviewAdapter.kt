package com.example.firebaseself.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseself.ModalClass.DataModal
import com.example.firebaseself.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class RecyclerviewAdapter(options: FirebaseRecyclerOptions<DataModal>
): FirebaseRecyclerAdapter<DataModal,RecyclerviewAdapter.MyViewHolder>(options)
{
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val id: TextView = itemView.findViewById(R.id.txtId)
        val name: TextView = itemView.findViewById(R.id.txtName)
        val coursename: TextView = itemView.findViewById(R.id.txtCourseName)
        val age: TextView = itemView.findViewById(R.id.txtAge)

        fun bind(model: DataModal) {
            id.text = model.Id
            name.text = model.Name
            coursename.text = model.CourseName
            age.text = model.Age

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_data_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: DataModal) {
        holder.bind(model)
    }

}