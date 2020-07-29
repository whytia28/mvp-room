package com.example.mvproom.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvproom.R
import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddView {

    private lateinit var addPresenter: AddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val mDb = StudentDatabase.getInstance(this)
        mDb?.let {
            addPresenter = AddPresenter(it, this)
        }

        btn_save.setOnClickListener {
            val objectStudent = Student(
                null,
                et_name.text.toString(),
                et_email.text.toString()
            )
            addPresenter.saveStudent(objectStudent)
        }
    }

    override fun showSuccessSave(student: Student) {
        runOnUiThread {
            Toast.makeText(this@AddActivity, "Sukses menambah ${student.name}", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    override fun showFailedSave(student: Student) {
        runOnUiThread {
            Toast.makeText(this@AddActivity, "Gagal menambah ${student.name}", Toast.LENGTH_SHORT).show()
        }
    }
}