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


    @Test
    fun shouldCreateAListOfNTransactionsUsingResponseData() {
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

        val result = dataAdapterService.listOfNTransactions(data)

        assertThat(result).isNotEmpty()
        assertThat(result.get(0).value).isEqualTo(248135)
        assertThat(result.get(0).timestamp.toString()).isEqualTo("2024-04-06T16:31:41Z")
    }
}