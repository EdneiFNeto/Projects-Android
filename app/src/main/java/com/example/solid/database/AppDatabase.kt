package com.example.solid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.solid.database.dao.UserDao
import com.example.solid.database.entity.UserEntity

private const val DB = "app_db"

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private lateinit var db:AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if(::db.isInitialized){
                return db
            }

            db =  Room
                .databaseBuilder(context, AppDatabase::class.java, DB)
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

}