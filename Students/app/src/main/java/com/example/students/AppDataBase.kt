package com.example.students

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonModel::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {

        @Volatile private var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it}
            }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, "application.db")
                .build()
    }
}