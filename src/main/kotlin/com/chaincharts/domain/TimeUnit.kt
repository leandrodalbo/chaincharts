package com.chaincharts.domain

enum class TimeUnit(val value: String) {
    DAYS("days"),
    HOURS("hours"),
    NONE("none");

    override fun toString(): String = value
}