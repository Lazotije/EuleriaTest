package com.example.euleriatask.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.repository.MonitoringRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SelectDurationViewModel(private val monitoringRepo: MonitoringRepo) : ViewModel() {

    private val _heartRateAndOxygen = MutableStateFlow<HeartRateOxygenPair?>(null)
    val heartRateAndOxygen: StateFlow<HeartRateOxygenPair?> = _heartRateAndOxygen

    private fun startMonitoring() {
        viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it
            }
        }
    }

    fun startSession(minutes: Int) {
        //when user choose the button, timer should start and monitoring should start
    }

    fun pauseSession() {
        //todo when user taps the screen
    }

    fun resumeSession() {
        Log.d("LAZA", "RESUME SESSION")
    }

    fun cancelSession() {
        Log.d("LAZA", "CANCEL SESSION")
    }
}