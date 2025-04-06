package com.chaincharts.service

import org.jetbrains.kotlinx.dataframe.AnyFrame
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.springframework.stereotype.Service

@Service
class DataframeService(private val dataAdapterService: DataAdapterService) {

    fun volumePointsDataframe(data: Map<String, Any>): AnyFrame {
        val volumePoints = dataAdapterService.listOfVolumePoints(data)

        return dataFrameOf(
            "Time" to volumePoints.map { it.timestamp },
            "Volume (USD)" to volumePoints.map { it.value }
        )
    }
}