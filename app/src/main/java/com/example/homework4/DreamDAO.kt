package com.example.homework4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDAO {

    @Query("SELECT * FROM dream_table ORDER BY date DESC")
    fun getAlphaDreams(): Flow<List<Dream>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream:Dream)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun deleteByDream(id:Int)

    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun select(id:Int) : Flow<Dream>

    @Query("UPDATE dream_table SET name=:name, date=:date, contents=:contents, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun updateAll(id:Int, name:String, date:String, contents:String, reflection:String, emotion:String)
}