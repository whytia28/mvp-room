package com.example.mvproom.edit

import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditPresenter(val mDb: StudentDatabase, val listener: EditView) {

    fun editStudent(student: Student) {
        GlobalScope.launch {
            val result = mDb.studentDao().updateStudent(student)
                if (result != 0) {
                    listener.showSuccessEdit(student)
                }else {
                    listener.showFailedEdit(student)
                }

        }
    }
}