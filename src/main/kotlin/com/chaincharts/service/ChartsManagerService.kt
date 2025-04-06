package com.chaincharts.service

import org.springframework.stereotype.Service

@Service
class ChartsManagerService(private val dataService: DataService, private val chartGenerationService: ChartGenerationService) {

    fun btc365VolumeWithAvg(): String{
        return this.chartGenerationService.generate365BTCVolumeChart(dataService.fetchChainData("/charts/estimated-transaction-volume-usd?timespan=365days&format=json"))
    }
}