package com.example.euleriatask.ui.widgets

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun EuleriaLineChart(
    heartRateEntries: State<List<Entry>>,
    saturationEntries: State<List<Entry>>,
    seconds: Float,
    onClick: () -> Unit
) {
    val lineChart = LineChart(LocalContext.current)

    heartRateEntries.takeIf { it.value.isEmpty() }?.run {
        return
    }

    saturationEntries.takeIf { it.value.isEmpty() }?.run {
        return
    }

    lineChart.apply {
        // Set x an y axis
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = seconds

        axisLeft.axisMinimum = 60f
        axisLeft.axisMaximum = 181f

        //layout
        setDrawGridBackground(false)
        setDrawBorders(false)
        setBackgroundColor(Color.WHITE)
        description.text = ""

        axisLeft.setDrawGridLines(false)
        axisRight.setDrawGridLines(false)
        xAxis.setDrawGridLines(false)

        setScaleEnabled(false)

        setOnClickListener {
            onClick()
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 45.dp, end = 45.dp),
        factory = { lineChart }
    ) { androidView ->

        //heart rate
        val heartRateDataSet = LineDataSet(heartRateEntries.value, "Heart rate")
        heartRateDataSet.apply {
            color = Color.RED
            valueTextColor = Color.BLACK
            setDrawValues(false)
            setDrawCircles(false)

            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
        }


        //saturation
        val saturationDataSet = LineDataSet(saturationEntries.value, "Saturation")
        saturationDataSet.apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            setDrawValues(false)
            setDrawCircles(false)

            mode = LineDataSet.Mode.CUBIC_BEZIER
            cubicIntensity = 0.2f
        }

        //data for the plot
        val lineData = LineData(heartRateDataSet, saturationDataSet)
        androidView.data = lineData
        androidView.invalidate()
    }
}
