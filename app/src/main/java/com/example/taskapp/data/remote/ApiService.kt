package com.example.taskapp.data.remote

import com.example.taskapp.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @GET("/v3/b/6895ff14203a8b52b5e1d957?meta=false")
    suspend fun getTasks(): Response<List<TaskDto>>

    @POST("/v3/b?meta=false")
    @Headers(
        "Content-Type: application/json",
        "X-Master-Key: ${Constants.X_MASTER_KEY}"
    )
    suspend fun saveTask(@Body taskDto: TaskDto): Response<Unit>
}
