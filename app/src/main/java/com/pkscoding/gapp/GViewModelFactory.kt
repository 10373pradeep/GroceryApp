package com.pkscoding.gapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GViewModelFactory(private val repository: GRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GViewModel(repository) as T
    }
}