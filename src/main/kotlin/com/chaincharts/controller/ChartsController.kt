package com.chaincharts.controller

import com.chaincharts.service.ChartsManagerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/charts")
class ChartsController(private val chartsManagerService: ChartsManagerService) {

    @GetMapping("/btc365volume")
    fun btc365volume(): String = chartsManagerService.btc365VolumeWithAvg()

}