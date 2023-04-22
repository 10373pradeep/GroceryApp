package com.pkscoding.gapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GItems::class], version = 1)
abstract class GDataBase : RoomDatabase(){

    abstract fun getGDao() : GDaoInterF

    companion object{
        @Volatile
        private  var instance : GDataBase? = null
        private  val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createdatabse(context).also{
                instance = it
            }
        }

        private fun createdatabse(context: Context) =

            Room.databaseBuilder(
                context.applicationContext,
                GDataBase::class.java, "GroceryDatabase.db"
            ).build()


    }


}