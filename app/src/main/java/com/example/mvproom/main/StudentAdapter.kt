package com.example.mvproom.main

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvproom.R
import com.example.mvproom.model.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentAdapter(private val listStudent: List<Student>, val presenter: MainPresenter) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listStudent.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_id.text = listStudent[position].id.toString()
        holder.itemView.tv_nama.text = listStudent[position].name
        holder.itemView.tv_email.text = listStudent[position].email
        holder.itemView.iv_edit.setOnClickListener {
            presenter.goToEditActivity(listStudent[position])
        }

        holder.itemView.iv_delete.setOnClickListener {
            val builder = AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                presenter.deleteStudent(listStudent[position])
            }.setNegativeButton(
                "Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah anda mau menghapus data ${listStudent[position].name}")
                .setTitle("Konfirmasi hapus")
                .show()
            builder.create()
        }
    }


    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}