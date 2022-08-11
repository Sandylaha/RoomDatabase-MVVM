package com.example.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    val allData: LiveData<List<EntityPerson>>
    val repository : Repository

    init {
        val dao = DatabasePerson.getDataBase(application).getData()
        repository = Repository(dao)
        allData = repository.allData
    }

    fun deleteNote (entityPerson: EntityPerson) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entityPerson)
    }

    fun updateNote(entityPerson: EntityPerson) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(entityPerson)
    }

    fun addNote(entityPerson: EntityPerson) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entityPerson)
    }
}


