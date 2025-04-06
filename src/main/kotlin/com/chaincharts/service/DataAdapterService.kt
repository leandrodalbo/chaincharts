package com.chaincharts.service

import com.chaincharts.dataobjects.NTransactions
import com.chaincharts.dataobjects.VolumePoint
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DataAdapterService {

    fun listOfVolumePoints(data: Map<String, Any>): List<VolumePoint> {
        val values = extractValues(data)

        return values.map {
            VolumePoint(Instant.ofEpochSecond((it["x"] as Number).toLong()), it["y"] as Double)
        }
    }

    fun listOfNTransactions(data: Map<String, Any>): List<NTransactions> {
        val values = extractValues(data)

        return values.map {
            NTransactions(Instant.ofEpochSecond((it["x"] as Number).toLong()), (it["y"] as Number).toLong())
        }
    }

    private fun extractValues(data: Map<String, Any>) = data["values"] as List<Map<String, Any>>
}