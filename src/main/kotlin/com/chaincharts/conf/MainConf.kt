package com.chaincharts.conf

import com.chaincharts.props.DataProps
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient


@Configuration
class MainConf {

    @Bean
    fun webClient(builder: RestClient.Builder, dataProps: DataProps): RestClient {
        return builder.baseUrl(dataProps.apiUrl).build()
    }
}