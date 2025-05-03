package com.chaincharts.service

import org.jetbrains.kotlinx.dataframe.AnyFrame
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.springframework.stereotype.Service

@Service
class DataframeService(private val dataAdapterService: DataAdapterService) {

    fun timeVolumeDataframe(data: Map<String, Any>): AnyFrame {
        val volumePoints = dataAdapterService.timestampDoubleData(data)

        return dataFrameOf(
            "time" to volumePoints.map { it.timestamp },
            "volume" to volumePoints.map { it.value }
        )
    }

    fun timeTransactionsDataframe(data: Map<String, Any>): AnyFrame {
        val volumePoints = dataAdapterService.timestampLongData(data)

        return dataFrameOf(
            "time" to volumePoints.map { it.timestamp },
            "transactions" to volumePoints.map { it.value }
        )
    }


}