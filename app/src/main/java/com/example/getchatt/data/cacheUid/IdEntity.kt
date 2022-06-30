package com.example.getchatt.data.cacheUid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Id")
data class IdEntity(
    @ColumnInfo(name = "Uid")
    var Uid: String
)
