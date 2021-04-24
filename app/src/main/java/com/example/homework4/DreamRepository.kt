package com.example.homework4

import kotlinx.coroutines.flow.Flow

class DreamRepository (private val dreamDAO: DreamDAO) {
    // From Kotlin example

    val allDreams : Flow<List<Dream>> = dreamDAO.getAlphaDreams()

    // All functions

    suspend fun insert (dream: Dream) {
        dreamDAO.insert(dream)
    }

    suspend fun deleteByDream (id:Int) {
        dreamDAO.deleteByDream(id)

    }

    fun select (id:Int): Flow<Dream> {
        return dreamDAO.select(id)
    }

    suspend fun updateAll (id:Int, name:String, date:String, contents:String, reflection:String, emotion:String ) {
        dreamDAO.updateAll(id, name, date, contents, reflection, emotion)
    }
}