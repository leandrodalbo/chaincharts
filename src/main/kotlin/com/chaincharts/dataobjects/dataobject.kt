package com.chaincharts.dataobjects

import java.time.Instant

data class VolumePoint(val timestamp: Instant, val value: Double)
data class NTransactions(val timestamp: Instant, val value: Long)
