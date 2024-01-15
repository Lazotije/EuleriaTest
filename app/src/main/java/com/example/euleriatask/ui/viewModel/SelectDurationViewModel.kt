package com.example.euleriatask.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.repository.MonitoringRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SelectDurationViewModel(private val monitoringRepo: MonitoringRepo) : ViewModel() {

    private val _heartRateAndOxygen = MutableStateFlow<HeartRateOxygenPair?>(null)
    val heartRateAndOxygen: StateFlow<HeartRateOxygenPair?> = _heartRateAndOxygen

    private var monitoringJob: Job? = null

    
    private fun startMonitoring() {
        monitoringJob = viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it
            }
        }
    }

    fun startSession(minutes: Int) {
        Log.d("LAZA", "POCINJE SESIJA OD $minutes minuta" )
        monitoringJob = viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it
            }
        }
    }

    fun pauseSession() {
        monitoringJob?.cancel()
    }

    fun resumeSession() {
        startMonitoring()
    }

    fun cancelSession() {
        monitoringJob?.cancel()
        _heartRateAndOxygen.value = null
    }


    companion object {
        val SECONDS = 60
        val MILISECONDS = 1000
    }
}