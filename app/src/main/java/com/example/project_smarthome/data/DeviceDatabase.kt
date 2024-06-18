package com.example.project_smarthome.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Device::class], version = 1, exportSchema = false)
abstract class DeviceDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile
        private var Instance: DeviceDatabase? = null
        fun getDatabase(context: Context): DeviceDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DeviceDatabase::class.java,
                    "device_database"
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}