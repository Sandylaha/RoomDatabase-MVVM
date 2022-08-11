package com.example.roomdatabase

import androidx.lifecycle.LiveData

class Repository(private val daoPerson: DaoPerson) {
val allData: LiveData<List<EntityPerson>> = daoPerson.getData()

suspend fun insert(entityPerson: EntityPerson) {
    daoPerson.insertDao(entityPerson)
}

suspend fun delete(entityPerson: EntityPerson){
    daoPerson.deleteDao(entityPerson)
}

suspend fun update(entityPerson: EntityPerson){
    daoPerson.updateDao(entityPerson)
}
}