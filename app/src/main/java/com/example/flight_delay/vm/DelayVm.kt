package com.example.flight_delay.vm

import androidx.lifecycle.ViewModel
import com.example.flight_delay.data.repo.ResponseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DelayVm @Inject constructor(
    private val responseRepo: ResponseRepo
): ViewModel() {

}
