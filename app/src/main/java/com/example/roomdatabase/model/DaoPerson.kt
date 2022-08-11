package com.example.roomdatabase.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoPerson {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDao(entityPerson: EntityPerson)

    @Update
    suspend fun updateDao(entityPerson: EntityPerson)

    @Delete
    suspend fun deleteDao(entityPerson: EntityPerson)

    @Query("select * from Person order by name ASC")
    fun getData(): LiveData<List<EntityPerson>>
}