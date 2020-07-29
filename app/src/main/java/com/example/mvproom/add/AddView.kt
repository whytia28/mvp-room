package com.example.mvproom.add

import com.example.mvproom.model.Student

interface AddView {
    fun showSuccessSave(student: Student)

    fun showFailedSave(student: Student)

}