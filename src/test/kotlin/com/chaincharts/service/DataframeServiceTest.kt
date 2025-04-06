package com.chaincharts.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataframeServiceTest {

    private var dataframeService = DataframeService(DataAdapterService())

    @Test
    fun shouldCreateADataframeWithVolumePoints() {
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

        val result = dataframeService.volumePointsDataframe(data)

        assertThat(result).isNotNull
        assertThat(result["time"]).isNotNull
        assertThat(result["volume"]).isNotNull
    }

    @Test
    fun shouldCreateADataframeWithTransactionsData() {
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

        val result = dataframeService.nTransactionsDataframe(data)

        assertThat(result).isNotNull
        assertThat(result["time"]).isNotNull
        assertThat(result["transactions"]).isNotNull
    }
}