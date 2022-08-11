package com.example.roomdatabase.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityPerson::class], version = 1, exportSchema = false)
abstract class DatabasePerson : RoomDatabase() {
    abstract fun getData(): DaoPerson

    companion object {
        @Volatile
        private var INSTANCE: DatabasePerson? = null
        fun getDataBase(context: Context): DatabasePerson {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabasePerson::class.java,
                    "database_person"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}