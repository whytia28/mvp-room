package com.example.mvproom.add

import android.content.Context
import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPresenter(val mDb: StudentDatabase,val listener: AddView) {



    fun saveStudent(student: Student) {
        GlobalScope.launch {
            val result = mDb.studentDao().insert(student)
                if (result != 0.toLong()) {
                    listener.showSuccessSave(student)
                } else {
                    listener.showFailedSave(student)
                }

        }
    }
}