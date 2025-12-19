package com.example.flight_delay.data.remote

import com.example.flight_delay.data.model.Request
import com.example.flight_delay.data.model.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ResponseApi {
    @POST("predict")
    suspend fun getDetails(
        @Body request: Request
    ): Response
}
