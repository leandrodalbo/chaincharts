package com.chaincharts.service

import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.XYChart
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class ChartDispatcherService {

    fun chartUri(chart: XYChart, chartKey: String): String {
        val outputPath = Paths.get("/home/leandro/charts/${chartKey}.png").toAbsolutePath().toString()
        BitmapEncoder.saveBitmap(chart, outputPath, BitmapEncoder.BitmapFormat.PNG)
        return outputPath
    }

}