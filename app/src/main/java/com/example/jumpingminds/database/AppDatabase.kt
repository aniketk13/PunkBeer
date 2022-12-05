package com.example.jumpingminds.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(DateConverter::class)
@Database(entities = [PunkEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao():PunkDao

    companion object{
        private var INSTANCE:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase?{
            if(INSTANCE==null){
                synchronized(AppDatabase::class){
                    INSTANCE= Room.databaseBuilder(context,AppDatabase::class.java,"Punk.db").build()
                }
            }
            return INSTANCE
        }
    }
}