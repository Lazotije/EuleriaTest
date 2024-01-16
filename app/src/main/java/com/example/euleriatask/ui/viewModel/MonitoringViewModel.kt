package com.example.euleriatask.ui.viewModel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.repository.MonitoringRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MonitoringViewModel(private val monitoringRepo: MonitoringRepo) : ViewModel() {

    private val _heartRateAndOxygen = MutableStateFlow<HeartRateOxygenPair?>(null)
    val heartRateAndOxygen: StateFlow<HeartRateOxygenPair?> = _heartRateAndOxygen

    private val _timerFinished = MutableStateFlow(false)
    val timerFinished: StateFlow<Boolean> = _timerFinished

    private var monitoringJob: Job? = null

    private lateinit var countDownTimer: CountDownTimer
    private var remainingTime: Int = -1

    fun startSession(remainingSeconds: Int) {
        Log.d("LAZA", "POCINJE SESIJA OD $remainingSeconds sekundi")

        monitoringJob = viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it
            }
        }

        countDownTimer = object : CountDownTimer(remainingSeconds * MILLISECONDS, MILLISECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("LAZA", "PREOSTALO VREMENA " + millisUntilFinished / MILLISECONDS)
                remainingTime = (millisUntilFinished / MILLISECONDS).toInt()
            }

            override fun onFinish() {
                Log.d("LAZA", "ON FINISHED")
                _timerFinished.value = true
            }
        }
        countDownTimer.start()
    }

    fun pauseSession() {
        Log.d("LAZA", "PAUZIRAJ ")
        countDownTimer.cancel()
        monitoringJob?.cancel()
    }

    fun resumeSession() {
        Log.d("LAZA", "RESUME SESSION ")
        startSession(remainingTime)
    }

    private fun cancelSession() {
        countDownTimer.cancel()
        monitoringJob?.cancel()
        _heartRateAndOxygen.value = null
    }

    fun dispose() {
        cancelSession()
    }

    companion object {
        const val MILLISECONDS: Long = 1000
    }
}