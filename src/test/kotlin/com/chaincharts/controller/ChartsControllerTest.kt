package com.chaincharts.controller

import com.chaincharts.service.ChartsManagerService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(ChartsController::class)
class ChartsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var chartsManagerService: ChartsManagerService

    @Test
    fun shouldGenerateBTC365VolumeChart() {
        every { chartsManagerService.btc365VolumeWithAvg() } returns "/tmp/my/btc365/volume/chart"

        val response = mockMvc.perform(get("/api/charts/btc365volume"))
            .andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { chartsManagerService.btc365VolumeWithAvg() }
    }
}