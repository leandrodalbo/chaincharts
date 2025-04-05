package com.chaincharts.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import java.io.IOException


class DataServiceTest {
    var mapper: ObjectMapper = ObjectMapper()

    private var mockWebServer: MockWebServer? = null

    private var dataService: DataService? = null

    @BeforeEach
    @Throws(IOException::class)
    fun setup() {
        this.mockWebServer = MockWebServer()
        mockWebServer!!.start()

        val restClient = RestClient.builder()
            .baseUrl(
                String.format(
                    "http://localhost:%s",
                    mockWebServer!!.port
                )
            )
            .build()

        this.dataService = DataService(restClient)
    }

    @AfterEach
    @Throws(IOException::class)
    fun clean() {
        mockWebServer!!.shutdown()
    }

    @Test
    @Throws(JsonProcessingException::class)
    fun shouldFetchBinanceSymbols() {
        val mockResponse = MockResponse()
            .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(
                mapper.writeValueAsString(
                    mapOf(
                        "status" to "ok",
                        "name" to "Estimated USD Transaction Value",
                        "unit" to "USD",
                        "period" to "day",
                        "description" to "The Estimated Transaction Value in USD value.",
                        "values" to listOf(
                            mapOf(
                                "x" to 1743724800,
                                "y" to 8780468949.850952
                            )
                        )
                    )
                )
            )

        mockWebServer!!.enqueue(mockResponse)

        val result =
            dataService?.fetchChainData("/charts/estimated-transaction-volume-usd?timespan=24hours&format=json")
                ?: emptyMap()

        assertThat(result["values"] as List<*>?).isNotEmpty()
    }
}