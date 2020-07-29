package com.example.mvproom.edit

import com.example.mvproom.model.Student

interface EditView {
    fun showSuccessEdit(student: Student)

    fun showFailedEdit(student: Student)
}