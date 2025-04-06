package com.chaincharts.controller

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import com.chaincharts.service.ChartInfoService
import com.chaincharts.service.ChartsManagerService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(ChartsController::class)
class ChartsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var chartsManagerService: ChartsManagerService

    private val chartInfoService = ChartInfoService()

    @Test
    fun shouldGetBTCVolumeChart() {
        val chartInfo: ChartInfo = chartInfoService.btcVolumeChartInfo(TimeUnit.DAYS, TimeValue.I365)
        every { chartsManagerService.btcVolumeChart(any<TimeUnit>(), any<TimeValue>()) } returns chartInfo

        val response = mockMvc.perform(
            get("/api/charts/btcvolumechart")
                .queryParam("timeUnit", TimeUnit.DAYS.name)
                .queryParam("timeValue", TimeValue.I365.name)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { chartsManagerService.btcVolumeChart(any<TimeUnit>(), any<TimeValue>()) }
    }

    @Test
    fun shouldGetBTCTransactionsChart() {
        val chartInfo: ChartInfo = chartInfoService.btcTransactionsChartInfo(TimeUnit.HOURS, TimeValue.I200)
        every { chartsManagerService.btcTransactionsChart(any<TimeUnit>(), any<TimeValue>()) } returns chartInfo

        val response = mockMvc.perform(
            get("/api/charts/btctransactionschart")
                .queryParam("timeUnit", TimeUnit.HOURS.name)
                .queryParam("timeValue", TimeValue.I200.name)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { chartsManagerService.btcTransactionsChart(any<TimeUnit>(), any<TimeValue>()) }
    }

    @Test
    fun shouldHandleInvalidInputs() {

        val response = mockMvc.perform(
            get("/api/charts/btcvolumechart")
                .queryParam("timeUnit", "FRUTA")
                .queryParam("timeValue", TimeValue.I365.name)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.BAD_REQUEST.value())

    }
}