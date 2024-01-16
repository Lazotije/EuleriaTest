package com.example.euleriatask.ui.widgets

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.delay


@Composable
fun EuleriaLineChart(newEntry: Entry) {
    val lineChart = LineChart(LocalContext.current)


    // Dodajte podatke u lineChart
//    val entries = listOf(
//        Entry(0f, 10f),
//        Entry(1f, 20f),
//        Entry(2f, 15f),
//        Entry(3f, 15f),
//        Entry(4f, 15f),
//        Entry(5f, 15f),
//        Entry(6f, 15f),
//        Entry(7f, 10f),
//        Entry(8f, 20f),
//        Entry(9f, 15f),
//        Entry(10f, 15f),
//        Entry(11f, 15f),
//        Entry(12f, 15f),
//        Entry(13f, 15f)
        // Dodajte više tačaka prema potrebi
//    )

    val entries: MutableList<Entry> = mutableListOf()
    entries.add(newEntry)

    val dataSet = LineDataSet(entries, "Label")
    dataSet.color = Color.BLUE
    dataSet.valueTextColor = Color.BLACK

    val lineData = LineData(dataSet)
    lineChart.data = lineData

    // Postavite ostale opcije grafikona po potrebi
    val description = Description()
    description.text = "Ovo je opis grafikona"
    lineChart.description = description

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { lineChart }
    ) { androidView ->
        androidView.animateX(1000)
    }
}
