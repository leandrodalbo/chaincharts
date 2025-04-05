package com.chaincharts.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChartGenerationServiceTest {

    private var chartGenerationService = ChartGenerationService(DataframeService(DataAdapterService()))

    @Test
    fun shouldCreateA365BTCVolumesChart() {
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

        val result = chartGenerationService.generate365BTCVolumeChart(data)

        assertThat(result).isNotNull
        assertThat(result).isEqualTo("/home/leandro/charts/btc_volume_vs_avg.png")
    }
}