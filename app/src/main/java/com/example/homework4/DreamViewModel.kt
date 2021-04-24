package com.example.homework4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DreamViewModel (private val repository: DreamRepository) : ViewModel() {
    // From Kotlin Database example

    val allDreams: LiveData<List<Dream>> = repository.allDreams.asLiveData()

    fun insert(dream:Dream) = viewModelScope.launch{
        repository.insert(dream)
    }

    fun deleteByDream(id:Int) = viewModelScope.launch{
        repository.deleteByDream(id)
    }

    fun select(id:Int) : LiveData<Dream> {
        return repository.select(id).asLiveData()
    }

    fun updateAll(id:Int, name:String, date:String, contents:String, reflection:String, emotion:String) = viewModelScope.launch {
        repository.updateAll(id, name, date, contents, reflection, emotion)
    }
}

class DreamViewModelFactory(private val repository: DreamRepository) : ViewModelProvider.Factory {

    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(DreamViewModel::class.java)){
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown view model class")
    }
}