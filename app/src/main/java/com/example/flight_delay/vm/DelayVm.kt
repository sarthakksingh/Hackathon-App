package com.example.flight_delay.vm

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
import java.net.UnknownHostException
import javax.inject.Inject
import retrofit2.HttpException

@HiltViewModel
class DelayVm @Inject constructor(
    private val responseRepo: ResponseRepo
) : ViewModel() {

    private val _flightState = MutableStateFlow<UiState<Response>>(UiState.Loading)
    val flightState = _flightState.asStateFlow()

    fun fetchFlightDetails(origin: String, destination: String, timeDate: String) {
        viewModelScope.launch {
            _flightState.value = UiState.Loading
            try {
                val request = Request(
                    origin = origin,
                    destination = destination,
                    timeDate = timeDate
                )

                val response = responseRepo.getFlightDetails(request)

                if (response != null) {
                    _flightState.value = UiState.Success(response)
                } else {
                    _flightState.value = UiState.Error("Failed to fetch flight details")
                }
            } catch (e: UnknownHostException) {
                _flightState.value = UiState.NoInternet
            } catch (e: HttpException) {
                _flightState.value = UiState.ServerError
            } catch (e: Exception) {
                _flightState.value =
                    UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}
