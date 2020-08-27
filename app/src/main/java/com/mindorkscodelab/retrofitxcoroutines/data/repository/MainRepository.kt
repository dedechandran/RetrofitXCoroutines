package com.mindorkscodelab.retrofitxcoroutines.data.repository

import com.mindorkscodelab.retrofitxcoroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}