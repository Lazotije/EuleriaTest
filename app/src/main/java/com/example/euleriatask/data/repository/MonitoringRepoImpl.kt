package com.example.euleriatask.data.repository

import com.example.euleriatask.data.model.HeartRate
import com.example.euleriatask.data.model.HeartRateOxygenPair
import com.example.euleriatask.data.model.OxygenSaturation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Random

class MonitoringRepoImpl : MonitoringRepo {

    private val random = Random()
    override suspend fun getHeartRateAndOxygenLevel(): Flow<HeartRateOxygenPair> = flow {
       while (true) {
           val heartRate = random.nextInt(121) + 60 // 60-181 bpm
           val saturation = random.nextInt(16) + 85 // 85-100 %

           emit(HeartRateOxygenPair(HeartRate(heartRate), OxygenSaturation(saturation)))

           delay(1000) //make sure data is sent every second
       }
    }
}