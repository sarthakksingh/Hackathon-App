package com.example.flight_delay.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight_delay.data.model.Request
import com.example.flight_delay.data.model.Response
import com.example.flight_delay.data.repo.ResponseRepo
import com.example.flight_delay.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class DelayVm @Inject constructor(
    private val responseRepo: ResponseRepo
) : ViewModel() {

    private val _flightState = MutableStateFlow<UiState<Response>>(UiState.Idle)
    val flightState = _flightState.asStateFlow()
    private var lastRequest: Request? = null

    fun fetchFlightDetails(origin: String, destination: String, timeDate: String) {
        Log.d("DelayVm", "fetchFlightDetails called: $origin -> $destination, $timeDate")
        viewModelScope.launch {
            Log.d("DelayVm", "Launching coroutine")
            _flightState.value = UiState.Loading
            try {
                val request = Request(origin, destination, timeDate)
                Log.d("DelayVm", "Request created: $request")

                val response = responseRepo.getFlightDetails(request)
                Log.d("DelayVm", "Repo returned: $response")

                if (response != null) {
                    _flightState.value = UiState.Success(response)
                } else {
                    _flightState.value = UiState.Error("Failed to fetch flight details")
                }
            } catch (e: Exception) {
                Log.e("DelayVm", "Error: ${e.message}", e)
                _flightState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun retryLastRequest() {
        lastRequest?.let {
            fetchFlightDetails(it.origin, it.destination, it.timeDate)
        }
    }
}
