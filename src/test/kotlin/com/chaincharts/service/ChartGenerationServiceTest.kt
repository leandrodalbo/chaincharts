package com.chaincharts.service

import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChartGenerationServiceTest {

    private var chartGenerationService = ChartGenerationService(DataframeService(DataAdapterService()))

    private val chartInfoService = ChartInfoService()

    @Test
    fun shouldCreateABTCVolumeChart() {
        val data = mapOf(
            "status" to "ok",
            "name" to "Estimated USD Transaction Value",
            "unit" to "USD",
            "period" to "day",
            "description" to "The Estimated Transaction Value in USD value.",
            "values" to listOf(
                mapOf("x" to 1712275200, "y" to 7927852982.371402),
                mapOf("x" to 1712361600, "y" to 5025793841.248038),
                mapOf("x" to 1712448000, "y" to 5011808062.969637),
                mapOf("x" to 1712534400, "y" to 11418584778.487938),
                mapOf("x" to 1712620800, "y" to 10939569437.810043),
                mapOf("x" to 1712707200, "y" to 8679207271.980724),
            )
        )

        val chartInfo = chartInfoService.btcVolumeChartInfo(TimeUnit.DAYS, TimeValue.I200)
        val result = chartGenerationService.btcVolumeChart(data, chartInfo)

        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(chartInfo.chartTitle)
        assertThat(result.width).isEqualTo(chartInfo.width)
        assertThat(result.height).isEqualTo(chartInfo.height)
    }

    @Test
    fun shouldCreateABTCTransactionsChart() {
        val data = mapOf(
            "status" to "ok",
            "name" to "n-transactions",
            "unit" to "Transactions",
            "period" to "day",
            "description" to "The number of transactions per day",
            "values" to listOf(
                mapOf(
                    "x" to 1712421101,
                    "y" to 248135
                )
            )
        )

        val chartInfo = chartInfoService.btcTransactionsChartInfo(TimeUnit.DAYS, TimeValue.I200)
        val result = chartGenerationService.btcTransactionsChart(data, chartInfo)

        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(chartInfo.chartTitle)
        assertThat(result.width).isEqualTo(chartInfo.width)
        assertThat(result.height).isEqualTo(chartInfo.height)
    }

    @Test
    fun shouldCreateABTCTransactionsPerBlockChart() {
        val data = mapOf(
            "status" to "ok",
            "name" to "n-transactions-per-block",
            "unit" to "Transactions",
            "period" to "day",
            "description" to "The number of transactions per block",
            "values" to listOf(
                mapOf(
                    "x" to 1712421101,
                    "y" to 4304
                )
            )
        )

        val chartInfo = chartInfoService.transactionsPerBlockInfo(TimeUnit.DAYS, TimeValue.I200)
        val result = chartGenerationService.btcTransactionsPerBlockChart(data, chartInfo)

        assertThat(result).isNotNull
        assertThat(result.title).isEqualTo(chartInfo.chartTitle)
        assertThat(result.width).isEqualTo(chartInfo.width)
        assertThat(result.height).isEqualTo(chartInfo.height)

    }
}