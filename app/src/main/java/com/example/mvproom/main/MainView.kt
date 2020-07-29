package com.example.mvproom.main

import com.example.mvproom.model.Student


interface MainView {
    fun showListStudent(listStudent: List<Student>)

    fun goToEditActivity(student: Student)

    fun goToAddActivity()

    fun successDeleteMessage(student: Student)

    fun failedDeleteMessage(student: Student)
}