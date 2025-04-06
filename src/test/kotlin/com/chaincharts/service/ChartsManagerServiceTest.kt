package com.chaincharts.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class ChartsManagerServiceTest {

    private val chartGenerationService: ChartGenerationService = mockk()
    private val dataService: DataService = mockk()

    private val chartsManagerService: ChartsManagerService = ChartsManagerService(dataService, chartGenerationService)

    @Test
    fun shouldFetchDataAndGenerateA365daysBTCVolumeChart() {
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

        every { dataService.fetchChainData("/charts/estimated-transaction-volume-usd?timespan=365days&format=json") } returns data
        every { chartGenerationService.generate365BTCVolumeChart(any()) } returns "/home/leandro/charts/btc_volume_vs_avg.png"

        val result = chartsManagerService.btc365VolumeWithAvg()

        assertThat(result).isEqualTo("/home/leandro/charts/btc_volume_vs_avg.png")

        verify { dataService.fetchChainData("/charts/estimated-transaction-volume-usd?timespan=365days&format=json") }
        verify { chartGenerationService.generate365BTCVolumeChart(data) }
    }
}