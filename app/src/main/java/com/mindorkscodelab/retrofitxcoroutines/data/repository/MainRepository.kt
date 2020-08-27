package com.mindorkscodelab.retrofitxcoroutines.data.repository

import com.mindorkscodelab.retrofitxcoroutines.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}