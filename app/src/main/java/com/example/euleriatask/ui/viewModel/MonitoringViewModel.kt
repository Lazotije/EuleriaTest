package com.example.euleriatask.ui.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.repository.MonitoringRepo
import com.example.euleriatask.ui.utiils.extensions.toMilliseconds
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MonitoringViewModel(private val monitoringRepo: MonitoringRepo) : ViewModel() {

    private val _heartRateAndOxygen = MutableStateFlow<HeartRateOxygenPair?>(null)
    val heartRateAndOxygen: StateFlow<HeartRateOxygenPair?> = _heartRateAndOxygen

    private val _timerFinished = MutableStateFlow(false)
    val timerFinished: StateFlow<Boolean> = _timerFinished

    private val _elapsedTime = MutableStateFlow("00:00")
    val elapsedTime: StateFlow<String> = _elapsedTime

    private val _heartRateEntryListUpdated = MutableStateFlow(emptyList<Entry>())
    val heartRateEntryListUpdated: StateFlow<List<Entry>> = _heartRateEntryListUpdated

    private val _saturationEntryListUpdated = MutableStateFlow(emptyList<Entry>())
    val saturationEntryListUpdated: StateFlow<List<Entry>> = _saturationEntryListUpdated

    private var monitoringJob: Job? = null

    private lateinit var countDownTimer: CountDownTimer
    private var remainingTime: Int = -1
    private var elapsedSeconds: Int = 0
    private var rememberElapsedSeconds: Int = 0

    fun startSession(remainingSeconds: Int) {

        monitoringJob = viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it

                _heartRateEntryListUpdated.value =
                    _heartRateEntryListUpdated.value.toMutableList().apply {
                        add(Entry(elapsedSeconds.toFloat(), it.rate.bpm.toFloat()))
                    }

                _saturationEntryListUpdated.value =
                    _saturationEntryListUpdated.value.toMutableList().apply {
                        add(Entry(elapsedSeconds.toFloat(), it.saturation.percentage.toFloat()))
                    }
            }
        }

        countDownTimer = object : CountDownTimer(remainingSeconds.toMilliseconds(), MILLISECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = (millisUntilFinished / MILLISECONDS).toInt()
                elapsedSeconds = rememberElapsedSeconds + (remainingSeconds - remainingTime)

                val minutes = elapsedSeconds / 60
                val seconds = elapsedSeconds % 60
                _elapsedTime.value = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                _timerFinished.value = true
            }
        }
        countDownTimer.start()
    }

    fun pauseSession() {
        countDownTimer.cancel()
        monitoringJob?.cancel()
        rememberElapsedSeconds = elapsedSeconds
    }

    fun resumeSession() {
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