package com.mindorkscodelab.retrofitxcoroutines.data.api

import com.mindorkscodelab.retrofitxcoroutines.data.model.User

interface ApiHelper {
    suspend fun getUsers() : List<User>
}