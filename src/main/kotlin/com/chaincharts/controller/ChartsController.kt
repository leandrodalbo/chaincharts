package com.chaincharts.controller

import com.chaincharts.domain.ChartInfo
import com.chaincharts.domain.TimeUnit
import com.chaincharts.domain.TimeValue
import com.chaincharts.service.ChartsManagerService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/charts")
class ChartsController(private val chartsManagerService: ChartsManagerService) {

    @GetMapping("/btcvolumechart")
    fun btcVolume(@RequestParam timeUnit: TimeUnit, @RequestParam timeValue: TimeValue): ChartInfo =
        chartsManagerService.btcVolumeChart(timeUnit, timeValue)

    @GetMapping("/btctransactionschart")
    fun btcTransactions(@RequestParam timeUnit: TimeUnit, @RequestParam timeValue: TimeValue): ChartInfo =
        chartsManagerService.btcTransactionsChart(timeUnit, timeValue)

}