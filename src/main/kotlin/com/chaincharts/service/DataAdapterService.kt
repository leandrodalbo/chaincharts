package com.chaincharts.service

import com.chaincharts.dataobjects.VolumePoint
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DataAdapterService {

    fun listOfVolumePoints(data: Map<String, Any>): List<VolumePoint> {
        val values = data["values"] as List<Map<String, Any>>

        return values.map {
            VolumePoint(Instant.ofEpochSecond((it["x"] as Number).toLong()), it["y"] as Double)
        }
    }
}