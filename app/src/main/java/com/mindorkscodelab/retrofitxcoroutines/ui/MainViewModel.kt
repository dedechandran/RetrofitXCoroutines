package com.mindorkscodelab.retrofitxcoroutines.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mindorkscodelab.retrofitxcoroutines.data.repository.MainRepository
import com.mindorkscodelab.retrofitxcoroutines.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = mainRepository.getUsers()
            emit(Resource.success(data = data))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured"))
        }
    }
}