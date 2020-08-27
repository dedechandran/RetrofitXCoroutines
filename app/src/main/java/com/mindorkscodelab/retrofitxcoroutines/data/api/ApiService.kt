package com.mindorkscodelab.retrofitxcoroutines.data.api

import com.mindorkscodelab.retrofitxcoroutines.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}