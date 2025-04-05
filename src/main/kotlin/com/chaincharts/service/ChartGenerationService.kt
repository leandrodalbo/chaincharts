package com.chaincharts.service

import kotlinx.datetime.LocalDate
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import org.springframework.stereotype.Service
import java.awt.BasicStroke
import java.awt.Color
import java.nio.file.Paths
import java.time.Instant
import java.time.ZoneId
import java.util.Date

@Service
class ChartGenerationService(private val dataframeService: DataframeService) {

    fun generate365BTCVolumeChart(data: Map<String, Any>): String {
        val df = dataframeService.volumePointsDataframe(data)

        val dates = (df["Time"].toList() as List<Instant>).map { Date.from(it) }
        val volumes = df["Volume (USD)"].toList() as List<Double>

        val avg = volumes.dropLast(1).average()
        val lastVolume = volumes.last()
        val lastDate = dates.last()
        val aboveAvg = lastVolume > avg

        val chart = XYChartBuilder()
            .width(1000).height(600)
            .title("BTC Transaction Volume vs 365-day Average")
            .xAxisTitle("Date")
            .yAxisTitle("Volume (USD)")
            .build()

        chart.addSeries("Volume", dates, volumes)
        chart.addSeries("Avg", dates, List(dates.size) { avg }).apply {
            lineColor = Color.ORANGE
            lineStyle = BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0f, floatArrayOf(6f), 0f)
        }

        chart.addSeries("Today", listOf(lastDate), listOf(lastVolume)).apply {
            marker = SeriesMarkers.CIRCLE
            markerColor = if (aboveAvg) Color.GREEN else Color.RED
        }

        val outputPath = Paths.get("/home/leandro/charts/btc_volume_vs_avg.png").toAbsolutePath().toString()

        BitmapEncoder.saveBitmap(chart, outputPath, BitmapEncoder.BitmapFormat.PNG)

        return outputPath
    }
}