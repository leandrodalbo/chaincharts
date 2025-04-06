package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue

import org.springframework.stereotype.Service


@Service
class ChartsManagerService(
    private val dataService: DataService,
    private val chartGenerationService: ChartGenerationService,
    private val chartInfoService: ChartInfoService,
    private val chartDispatcherService: ChartDispatcherService
) {

    fun btcVolumeChart(timeUnit: TimeUnit, timeValue: TimeValue): ChartInfo {
        val chartInfo = chartInfoService.btcVolumeChartInfo(timeUnit, timeValue)
        val chartData = dataService.fetchData(chartInfo.dataUri)
        val chart = this.chartGenerationService.btcVolumeChart(chartData, chartInfo)
        val chartUri = chartDispatcherService.chartUri(chart, chartInfo.assetKey)

        return chartInfo.copy(assetUri = chartUri)
    }

    fun btcTransactionsChart(timeUnit: TimeUnit, timeValue: TimeValue): ChartInfo {
        val chartInfo = chartInfoService.btcTransactionsChartInfo(timeUnit, timeValue)
        val chartData = dataService.fetchData(chartInfo.dataUri)
        val chart = this.chartGenerationService.btcTransactionsChart(chartData, chartInfo)
        val chartUri = chartDispatcherService.chartUri(chart, chartInfo.assetKey)

        return chartInfo.copy(assetUri = chartUri)
    }


}