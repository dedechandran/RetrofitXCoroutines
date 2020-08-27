package com.mindorkscodelab.retrofitxcoroutines.data.api

import com.mindorkscodelab.retrofitxcoroutines.data.model.User
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}