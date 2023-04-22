package com.pkscoding.gapp

import android.widget.RemoteViews.RemoteCollectionItems

class GRepository(private val db:GDataBase) {

    suspend fun insert(items: GItems) = db.getGDao().insert(items)
    suspend fun delete(items: GItems) = db.getGDao().delete(items)

    fun getAllItems() = db.getGDao().getAllGItems()
}