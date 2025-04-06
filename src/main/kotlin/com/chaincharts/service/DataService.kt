package com.chaincharts.service

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class DataService(private val restClient: RestClient) {

    fun fetchData(urlPath: String): Map<String, Any> {
        return restClient.get()
            .uri(urlPath)
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .body<Map<String, Any>>() ?: emptyMap()
    }
}