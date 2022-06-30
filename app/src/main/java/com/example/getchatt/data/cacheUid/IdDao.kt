package com.example.getchatt.data.cacheUid

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getchatt.data.cacheUid.IdEntity

@Dao
interface IdDao {

    @Query("SELECT * FROM `User Id`")
    fun getUid() : LiveData<IdEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: IdEntity)

    @Query("DELETE FROM `User Id`")
    suspend fun deleteUid()
}