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
            chartTitle = "BTC Transaction Volume vs ${timeValue.value}-${timeUnit.value} Average",
            xAxisTitle = "Date",
            yAxisTitle = "Volume (USD)",
            seriesA = "Volume",
            seriesB = "Avg",
            seriesC = "Today",
            width = 1800,
            height = 1200
        )

    }

    fun generateChartKey(dataUri: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(dataUri.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }

}