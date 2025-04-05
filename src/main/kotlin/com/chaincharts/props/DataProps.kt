package com.chaincharts.props

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dataConf")
data class DataProps(val apiUrl: String, ) {
}