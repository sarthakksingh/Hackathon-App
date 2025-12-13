package com.example.flight_delay.data.repo

import android.util.Log
import com.example.flight_delay.data.model.Request
import com.example.flight_delay.data.model.Response
import com.example.flight_delay.data.remote.ResponseApi
import javax.inject.Inject

class ResponseRepo @Inject constructor(
    private val responseApi: ResponseApi
){
    suspend fun getFlightDetails(request: Request): Response{
        return try{
            responseApi.getDetails(request)
        }catch (e: Exception){
            Log.e("SaveForLaterRepo", "Failed to add to save for later", e)
        } as Response
    }
}