package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.XYChart
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class ChartsManagerService(
    private val dataService: DataService,
    private val chartGenerationService: ChartGenerationService,
    private val chartInfoService: ChartInfoService
) {

    fun btcVolumeChart(timeUnit: TimeUnit, timeValue: TimeValue): ChartInfo {
        val chartInfo = chartInfoService.btcVolumeChartInfo(timeUnit, timeValue)
        val chartData = dataService.fetchData(chartInfo.dataUri)
        val chart = this.chartGenerationService.btcVolumeChart(chartData, chartInfo)

        return chartInfo.copy(assetUri = chartUri(chart, chartInfo.assetKey))
    }


    private fun chartUri(chart: XYChart, chartKey: String): String {
        val outputPath = Paths.get("/home/leandro/charts/${chartKey}.png").toAbsolutePath().toString()
        BitmapEncoder.saveBitmap(chart, outputPath, BitmapEncoder.BitmapFormat.PNG)
        return outputPath
    }

}