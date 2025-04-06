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
        assertThat(result.assetKey).isNotEmpty()
    }
}