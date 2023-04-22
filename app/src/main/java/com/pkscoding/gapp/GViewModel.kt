package com.pkscoding.gapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GViewModel(private  val repository: GRepository) : ViewModel(){

    fun insert(item:GItems) = GlobalScope.launch {
        repository.insert(item)
    }

    fun delete(item:GItems) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllGroceryItems() = repository.getAllItems()
}