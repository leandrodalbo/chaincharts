package com.chaincharts.domain


data class ChartInfo(
    val timeUnit: TimeUnit,
    val timeValue: TimeValue,
    val dataUri: String,
    val assetKey: String,
    val assetUri: String,
    val chartTitle: String,
    val xAxisTitle: String,
    val yAxisTitle: String,
    val seriesA: String,
    val seriesB: String,
    val seriesC: String,
    val width: Int,
    val height: Int
)

