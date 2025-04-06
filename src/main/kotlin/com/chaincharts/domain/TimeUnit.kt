package com.chaincharts.domain

enum class TimeUnit(val value: String) {
    DAYS("days"),
    HOURS("hours");

    override fun toString(): String = value
}