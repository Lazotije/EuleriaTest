package com.example.euleriatask.ui.viewModel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _navigateEvent = MutableLiveData<Unit>()
    val navigateEvent: LiveData<Unit> = _navigateEvent

    init {
        Log.d("LAZA", "INIT SELECT DURATION VIEWMODELA")
        startMonitoring()
    }

    private fun startMonitoring() {
        viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it
            }
        }
    }

    fun startSession(minutes: Int) {

    }

    fun pauseSession() {

    }

    fun resumeSession() {

    }

    fun cancelSession() {

    }


    companion object {
        val SECONDS = 60
        val MILISECONDS = 1000
    }
}