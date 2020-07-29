package com.example.mvproom.main

import android.content.Context
import com.example.mvproom.model.Student
import com.example.mvproom.model.StudentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(context: Context, private val listener: MainView) {
    private var mDb = StudentDatabase.getInstance(context)

    fun goToAddActivity() {
        listener.goToAddActivity()
    }

    fun goToEditActivity(student: Student) {
        listener.goToEditActivity(student)
    }

    fun deleteStudent(student: Student) {
        GlobalScope.launch {
            val result = mDb?.studentDao()?.deleteStudent(student)
           if (result != 0){
               listener.successDeleteMessage(student)
           } else {
               listener.failedDeleteMessage(student)
           }
        }
        getListStudent()
    }

    fun getListStudent() {

        GlobalScope.launch {
            val listStudent = mDb?.studentDao()?.getAllStudent()
            listStudent?.let {
                listener.showListStudent(it)
            }

        }
    }
}