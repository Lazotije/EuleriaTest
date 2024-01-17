package com.example.euleriatask.ui.viewModel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.repository.MonitoringRepo
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Random


class MonitoringViewModel(private val monitoringRepo: MonitoringRepo) : ViewModel() {

    private val _heartRateAndOxygen = MutableStateFlow<HeartRateOxygenPair?>(null)
    val heartRateAndOxygen: StateFlow<HeartRateOxygenPair?> = _heartRateAndOxygen

    private val _timerFinished = MutableStateFlow(false)
    val timerFinished: StateFlow<Boolean> = _timerFinished

    private val _elapsedTime = MutableStateFlow("00:00")
    val elapsedTime: StateFlow<String> = _elapsedTime

    private val _entryListUpdated = MutableStateFlow(emptyList<Entry>())
    val entryListUpdated: StateFlow<List<Entry>> = _entryListUpdated

    private var monitoringJob: Job? = null

    private lateinit var countDownTimer: CountDownTimer
    private var remainingTime: Int = -1
    private var elapsedSeconds: Int = 0
    private var rememberElapsedSeconds: Int = 0


    private val entryList: MutableList<Entry> = mutableListOf()

    var indexX = 0
    var i = 0
    var j = 0

    fun startSession(remainingSeconds: Int) {

        monitoringJob = viewModelScope.launch {
            monitoringRepo.getHeartRateAndOxygenLevel().collect {
                _heartRateAndOxygen.value = it

                Log.d("LAZA", "COLLECT " + it)
//                entryList.add(Entry(it.rate.bpm.toFloat(), remainingSeconds.toFloat()))
//
//                when(indexX%2) {
//                    0 -> {
//                        entryList.add(Entry((0+i++).toFloat(), 10f))
//                    }
//                    1 -> {
//                        entryList.add(Entry((1+j++).toFloat(), 20f))
//                    }
//                }
//
//                _entryListUpdated.value = entryList

                val newEntryValue = if (_entryListUpdated.value.size % 2 == 0) {
                    10f
                } else {
                    20f
                }

                val newEntry = Entry(_entryListUpdated.value.size.toFloat(), newEntryValue)
                _entryListUpdated.value = _entryListUpdated.value.toMutableList().apply {
                    add(newEntry)
                }
            }
        }

        countDownTimer = object : CountDownTimer(remainingSeconds * MILLISECONDS, MILLISECONDS) {
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