package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeValue
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries
import org.knowm.xchart.style.markers.SeriesMarkers
import org.springframework.stereotype.Service
import java.awt.BasicStroke
import java.awt.Color
import java.time.Instant
import java.util.Date

@Service
class ChartGenerationService(private val dataframeService: DataframeService) {

    fun btcVolumeChart(data: Map<String, Any>, chartInfo: ChartInfo): XYChart {
        val df = dataframeService.timeVolumeDataframe(data)

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

    fun btcTransactionsChart(data: Map<String, Any>, chartInfo: ChartInfo): XYChart {
        val df = dataframeService.timeTransactionsDataframe(data)

        val dates = (df["time"].toList() as List<Instant>).map { Date.from(it) }
        val transactions = df["transactions"].toList() as List<Long>

        val movingAvg = transactions.mapIndexed { index, _ ->
            if (index < TimeValue.I9.value.toInt()) null
            else transactions.subList(index - TimeValue.I9.value.toInt(), index + 1).average()
        }

        val minIndex = transactions.indices.minByOrNull { transactions[it] } ?: 0
        val maxIndex = transactions.indices.maxByOrNull { transactions[it] } ?: 0

        return XYChartBuilder()
            .width(chartInfo.width).height(chartInfo.height)
            .title(chartInfo.chartTitle)
            .xAxisTitle(chartInfo.xAxisTitle)
            .yAxisTitle(chartInfo.yAxisTitle)
            .build().apply {
                styler.apply {
                    defaultSeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Line
                    isLegendVisible = true
                    markerSize = 6
                }
                addSeries(chartInfo.seriesA, dates, transactions)
                addSeries(chartInfo.seriesB, dates, movingAvg)

                addSeries(
                    "${chartInfo.seriesC} (${dates[minIndex]})",
                    listOf(dates[minIndex]),
                    listOf(transactions[minIndex])
                ).apply {
                    marker = SeriesMarkers.CIRCLE
                }

                addSeries(
                    "${chartInfo.seriesD} (${dates[maxIndex]})",
                    listOf(dates[maxIndex]),
                    listOf(transactions[maxIndex])
                ).apply {
                    marker = SeriesMarkers.DIAMOND
                }
            }

    }

    fun btcTransactionsPerBlockChart(data: Map<String, Any>, chartInfo: ChartInfo): XYChart {
        val df = dataframeService.timeTransactionsDataframe(data)

        val dates = (df["time"].toList() as List<Instant>).map { Date.from(it) }
        val transactions = df["transactions"].toList() as List<Long>


        val minIndex = transactions.indices.minByOrNull { transactions[it] } ?: 0
        val maxIndex = transactions.indices.maxByOrNull { transactions[it] } ?: 0

        val chart = XYChartBuilder()
            .width(chartInfo.width).height(chartInfo.height)
            .title(chartInfo.chartTitle)
            .xAxisTitle(chartInfo.xAxisTitle)
            .yAxisTitle(chartInfo.yAxisTitle)
            .build()

        chart.addSeries(chartInfo.seriesA, dates, transactions)



        chart.addSeries(chartInfo.seriesB, listOf(dates[minIndex]), listOf(transactions[minIndex]))
            .apply {
                marker = SeriesMarkers.CIRCLE
                lineColor = Color.ORANGE
            }

        chart.addSeries(chartInfo.seriesC, listOf(dates[maxIndex]), listOf(transactions[maxIndex]))
            .apply {
                marker = SeriesMarkers.DIAMOND
                lineColor = Color.GREEN
            }


        return chart

    }
}