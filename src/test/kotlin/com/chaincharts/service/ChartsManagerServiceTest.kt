package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder


class ChartsManagerServiceTest {

    private val chartGenerationService: ChartGenerationService = mockk()
    private val dataService: DataService = mockk()
    private val chartInfoService: ChartInfoService = ChartInfoService()

    private val chartsManagerService: ChartsManagerService =
        ChartsManagerService(dataService, chartGenerationService, chartInfoService)

    @Test
    fun shouldFetchDataAndGenerateABTCVolumeChart() {
        val data = mapOf(
            "status" to "ok",
            "name" to "Estimated USD Transaction Value",
            "unit" to "USD",
            "period" to "day",
            "description" to "The Estimated Transaction Value in USD value.",
            "values" to listOf(
                mapOf(
                    "x" to 1743724800,
                    "y" to 8780468949.850952
                )
            )
        )

        var chart: XYChart = XYChartBuilder()
            .width(100).height(200)
            .title("mychart")
            .xAxisTitle("x")
            .yAxisTitle("y")
            .build()

        every { dataService.fetchData(any<String>()) } returns data
        every { chartGenerationService.btcVolumeChart(any(), any<ChartInfo>()) } returns chart

        val result = chartsManagerService.btcVolumeChart(TimeUnit.DAYS, TimeValue.I200)

        assertThat(result.assetKey).isNotNull()
        assertThat(result.assetUri).contains(result.assetKey)

        verify { dataService.fetchData(any()) }
        verify { chartGenerationService.btcVolumeChart(any(), any()) }

    }
}