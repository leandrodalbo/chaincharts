package com.chaincharts.service

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class ChartInfoService {

    fun btcVolumeChartInfo(timeUnit: TimeUnit, timeValue: TimeValue): ChartInfo {
        val dataUri =
            "/charts/estimated-transaction-volume-usd?timespan=${timeValue.value}${timeUnit.value}&format=json"
        val key = generateChartKey(dataUri)
        return ChartInfo(
            timeUnit,
            timeValue,
            dataUri,
            key,
            assetUri = "",
            width = 1800,
            height = 1200,
            chartTitle = "BTC Transaction Volume vs ${timeValue.value}-${timeUnit.value} Average",
            xAxisTitle = "Date",
            yAxisTitle = "Volume (USD)",
            seriesA = "Volume",
            seriesB = "Avg",
            seriesC = "Today",
            seriesD = ""
        )

    }

    fun btcTransactionsChartInfo(timeUnit: TimeUnit, timeValue: TimeValue): ChartInfo {
        val dataUri =
            "/charts/n-transactions?timespan=${timeValue.value}${timeUnit.value}&format=json"
        val key = generateChartKey(dataUri)
        return ChartInfo(
            timeUnit,
            timeValue,
            dataUri,
            key,
            assetUri = "",
            width = 1800,
            height = 1200,
            chartTitle = "BTC Number of Transactions ${timeValue.value}-${timeUnit.value}",
            xAxisTitle = "Date",
            yAxisTitle = "Transactions",
            seriesA = "Transactions",
            seriesB = "Moving Avg",
            seriesC = "Min",
            seriesD = "Max"
        )

    }

    fun generateChartKey(dataUri: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(dataUri.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }

}