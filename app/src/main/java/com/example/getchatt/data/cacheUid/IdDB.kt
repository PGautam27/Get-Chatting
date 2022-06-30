package com.example.getchatt.data.cacheUid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [IdEntity::class], version = 1, exportSchema = false)
abstract class IdDB : RoomDatabase() {

    abstract fun IdDao():IdDao

    companion object {
        @Volatile
        private var INSTANCE: IdDB? = null

        fun getInstance(context: Context): IdDB {
            val sampleInstance = INSTANCE
            if (sampleInstance != null) {
                return sampleInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IdDB::class.java,
                    "IdDB"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}