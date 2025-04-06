package com.chaincharts.props

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dataconf")
data class DataProps(val apiUrl: String, ) {
}