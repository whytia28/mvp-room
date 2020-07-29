package com.example.mvproom.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mvproom.model.Student


@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudent(): List<Student>

    @Insert(onConflict = REPLACE)
    fun insert(student: Student): Long

    @Update
    fun updateStudent(student: Student?): Int

    @Delete
    fun deleteStudent(student: Student): Int
}