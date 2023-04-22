package com.pkscoding.gapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GDaoInterF {

    //Insert Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GItems)

    //Delete Data
    @Delete
    suspend fun delete(item: GItems)


    //Query

    @Query("SELECT * FROM grocerry_items")
    fun getAllGItems() : LiveData<List<GItems>>

}