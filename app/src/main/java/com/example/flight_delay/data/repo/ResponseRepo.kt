package com.example.flight_delay.data.repo

import android.util.Log
import com.example.flight_delay.data.model.Request
import com.example.flight_delay.data.model.Response
import com.example.flight_delay.data.remote.ResponseApi
import javax.inject.Inject

class ResponseRepo @Inject constructor(
    private val responseApi: ResponseApi
) {

    suspend fun getFlightDetails(request: Request): Response? {
        return try {
            Log.d("ResponseRepo", "Calling API with: $request")
            val response = responseApi.getDetails(request)
            Log.d("ResponseRepo", "API Success: ${response.origin}")
            response
        } catch (e: retrofit2.HttpException) {
            Log.e("ResponseRepo", "HTTP ${e.code()}: ${e.response()?.errorBody()?.string()}", e)
            null
        } catch (e: Exception) {
            Log.e("ResponseRepo", "Failed to fetch flight details: ${e.message}", e)
            null
        }
    }

}
