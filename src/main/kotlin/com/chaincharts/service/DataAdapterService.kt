package com.chaincharts.service

import com.chaincharts.dataobjects.TimestampLongPair
import com.chaincharts.dataobjects.TimestampDoublePair
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DataAdapterService {

    fun timestampDoubleData(data: Map<String, Any>): List<TimestampDoublePair> {
        val values = extractValues(data)

        return values.map {
            TimestampDoublePair(Instant.ofEpochSecond((it["x"] as Number).toLong()), it["y"] as Double)
        }
    }

    fun timestampLongData(data: Map<String, Any>): List<TimestampLongPair> {
        val values = extractValues(data)

        return values.map {
            TimestampLongPair(Instant.ofEpochSecond((it["x"] as Number).toLong()), (it["y"] as Number).toLong())
        }
    }

    private fun extractValues(data: Map<String, Any>) = data["values"] as List<Map<String, Any>>
}