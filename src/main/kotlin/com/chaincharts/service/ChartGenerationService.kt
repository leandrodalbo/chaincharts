package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import org.springframework.stereotype.Service
import java.awt.BasicStroke
import java.awt.Color
import java.time.Instant
import java.util.Date

@Service
class ChartGenerationService(private val dataframeService: DataframeService) {

    fun btcVolumeChart(data: Map<String, Any>, chartInfo: ChartInfo): XYChart {
        val df = dataframeService.volumePointsDataframe(data)

        val dates = (df["time"].toList() as List<Instant>).map { Date.from(it) }
        val volumes = df["volume"].toList() as List<Double>

        val avg = volumes.dropLast(1).average()
        val lastVolume = volumes.last()
        val lastDate = dates.last()
        val aboveAvg = lastVolume > avg

        val chart = XYChartBuilder()
            .width(chartInfo.width).height(chartInfo.height)
            .title(chartInfo.chartTitle)
            .xAxisTitle(chartInfo.xAxisTitle)
            .yAxisTitle(chartInfo.yAxisTitle)
            .build()

        chart.addSeries(chartInfo.seriesA, dates, volumes)
        chart.addSeries(chartInfo.seriesB, dates, List(dates.size) { avg }).apply {
            lineColor = Color.ORANGE
            lineStyle = BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0f, floatArrayOf(6f), 0f)
        }

        chart.addSeries(chartInfo.seriesC, listOf(lastDate), listOf(lastVolume)).apply {
            marker = SeriesMarkers.CIRCLE
            markerColor = if (aboveAvg) Color.GREEN else Color.RED
        }
        return chart
    }

}