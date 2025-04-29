package com.chaincharts.domain

enum class TimeValue(val value: String) {
    I365("365"),
    I200("200"),
    I55("55"),
    I21("21"),
    I9("9"),
    NONE("none");

    override fun toString(): String = value
}