package com.chaincharts.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataAdapterServiceTest {

    private var dataAdapterService = DataAdapterService()

    @Test
    fun shouldCreateAListOfVolumePointsUsingResponseData() {
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

        val result = dataAdapterService.listOfVolumePoints(data)

        assertThat(result).isNotEmpty()
        assertThat(result.get(0).value).isEqualTo(8780468949.850952)
        assertThat(result.get(0).timestamp.toString()).isEqualTo("2025-04-04T00:00:00Z")
    }
}