package com.example.mvproom.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvproom.R
import com.example.mvproom.add.AddActivity
import com.example.mvproom.edit.EditActivity
import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenterMain: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenterMain = MainPresenter(this, this)

        rv_student.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fab_add.setOnClickListener {
            presenterMain.goToAddActivity()
        }

        presenterMain.getListStudent()
    }

    override fun onResume() {
        super.onResume()
        presenterMain.getListStudent()
    }


    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroyInstance()
    }

    override fun showListStudent(listStudent: List<Student>) {
        runOnUiThread {
            val adapter = StudentAdapter(listStudent, presenterMain)
            rv_student.adapter = adapter
        }
    }

    override fun goToEditActivity(student: Student) {
        val intentEditActivity = Intent(this, EditActivity::class.java)
        intentEditActivity.putExtra("student", student)
        startActivity(intentEditActivity)
    }

    override fun goToAddActivity() {
        val addIntent = Intent(this, AddActivity::class.java)
        startActivity(addIntent)
    }

    override fun successDeleteMessage(student: Student) {

        runOnUiThread {
            Toast.makeText(
                this,
                "Berhasil menghapus data ${student.name}}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun failedDeleteMessage(student: Student) {
        runOnUiThread {
            Toast.makeText(
                this,
                "Gagal menghapus data ${student.name}}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}