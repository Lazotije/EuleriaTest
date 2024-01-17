package com.example.euleriatask.ui.widgets

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


@Composable
fun EuleriaLineChart(entries: State<List<Entry>>) {
    val lineChart = LineChart(LocalContext.current)

    if(entries.value.isEmpty()) {
        Log.d("LAZA", "ENTRIES JE PRAZAN")
        return
    } else {
        Log.d("LAZA", "ENTRIES VALUE " + entries.value)
    }








    // Postavite ostale opcije grafikona po potrebi
    val description = Description()
    description.text = "Ovo je opis grafikona"
    lineChart.description = description

    // Postavite opsege za X i Y ose
    lineChart.xAxis.axisMinimum = 0f
    lineChart.xAxis.axisMaximum = 60f

    lineChart.axisLeft.axisMinimum = 0f
    lineChart.axisLeft.axisMaximum = 20f

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 45.dp, end = 45.dp)

        ,
        factory = { lineChart }
    ) { androidView ->

        val dataSet = LineDataSet(entries.value, "Label")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)
        androidView.data = lineData
        androidView.invalidate()
    }
}
