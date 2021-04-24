package com.example.homework4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// From Kotlin Database Example
@Entity(tableName = "dream_table")
class Dream(@PrimaryKey (autoGenerate = true) val id:Int,
            @ColumnInfo(name="name") val name:String,
            @ColumnInfo(name="date") val date:String,
            @ColumnInfo(name="contents") val contents:String,
            @ColumnInfo(name="reflection") val reflection:String,
            @ColumnInfo(name="emotion") val emotion:String)

