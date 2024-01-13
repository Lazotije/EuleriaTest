package com.example.euleriatask.data.repository

import com.example.euleriatask.data.model.HeartRateOxygenPair
import kotlinx.coroutines.flow.Flow

interface MonitoringRepo {
   suspend fun getHeartRateAndOxygenLevel() : Flow<HeartRateOxygenPair>
}