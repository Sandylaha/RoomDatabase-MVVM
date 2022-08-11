package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Person")
class EntityPerson(
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Address") val address: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}