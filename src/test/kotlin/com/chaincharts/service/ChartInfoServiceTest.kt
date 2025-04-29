package com.chaincharts.service

import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChartInfoServiceTest {

    private val chartInfoService = ChartInfoService()

    @Test
    fun shouldCreateBTCVolumeChartMetadata() {
        val result = chartInfoService.btcVolumeChartInfo(TimeUnit.DAYS, TimeValue.I365)

        assertThat(result.chartTitle).contains(TimeUnit.DAYS.value)
        assertThat(result.chartTitle).contains(TimeValue.I365.toString())
        assertThat(result.dataUri).contains("estimated-transaction-volume-usd")
        assertThat(result.assetKey).isNotEmpty()
        assertThat(result.assetKey).isEqualTo(chartInfoService.generateChartKey(result.dataUri))
    }

    @Test
    fun shouldCreateBTCTransactionsChartMetadata() {
        val result = chartInfoService.btcTransactionsChartInfo(TimeUnit.DAYS, TimeValue.I365)

        assertThat(result.chartTitle).contains(TimeUnit.DAYS.value)
        assertThat(result.chartTitle).contains(TimeValue.I365.toString())
        assertThat(result.dataUri).contains("n-transactions")
        assertThat(result.assetKey).isNotEmpty()
        assertThat(result.assetKey).isEqualTo(chartInfoService.generateChartKey(result.dataUri))
    }

    @Test
    fun shouldCreateBTCTransactionsPerBlockChartMetadata() {
        val result = chartInfoService.transactionsPerBlockInfo(TimeUnit.HOURS, TimeValue.I9)

        assertThat(result.chartTitle).isEqualTo("N Transactions per block")
        assertThat(result.xAxisTitle).isEqualTo("Date")
        assertThat(result.yAxisTitle).isEqualTo("Transactions")
        assertThat(result.dataUri).contains("n-transactions-per-block")
        assertThat(result.assetKey).isEqualTo(chartInfoService.generateChartKey(result.dataUri))
    }

    @Test
    fun shouldGenerateTheChartKey() {
        val result = chartInfoService.generateChartKey("/chart/somevalue?p=days200&format=json")

        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo("ae8bc4aa63c04aedec477e7fc33908f3cd8ae73d6bffd9827b91f8fcc2decf04")
    }
}