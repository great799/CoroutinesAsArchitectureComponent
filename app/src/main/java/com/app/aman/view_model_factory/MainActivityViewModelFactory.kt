package com.app.aman.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.aman.repository.MainRepository
import com.app.aman.view_model.MainActivityViewModel

class MainActivityViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}