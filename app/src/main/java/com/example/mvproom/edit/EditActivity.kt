package com.example.mvproom.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvproom.R
import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.android.synthetic.main.activity_edit.*



class EditActivity : AppCompatActivity(), EditView {

    private lateinit var objectStudent: Student
    private lateinit var editPresenter: EditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

       val mDb = StudentDatabase.getInstance(this)
        mDb?.let {
            editPresenter = EditPresenter(it, this)
        }

        intent.getParcelableExtra<Student>("student")?.let {
            objectStudent = it
        }

        supportActionBar?.title = "Ubah data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        et_name.setText(objectStudent.name)
        et_email.setText(objectStudent.email)

        btn_save.setOnClickListener {
            objectStudent.name = et_name.text.toString()
            objectStudent.email = et_email.text.toString()
            editPresenter.editStudent(objectStudent)
        }
    }

    override fun showSuccessEdit(student: Student) {
        runOnUiThread {
            Toast.makeText(this@EditActivity, "Sukses mengubah ${student.name}", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    override fun showFailedEdit(student: Student) {
        runOnUiThread {
            Toast.makeText(this@EditActivity, "Gagal mengubah ${student.name}", Toast.LENGTH_SHORT).show()
        }
    }
}