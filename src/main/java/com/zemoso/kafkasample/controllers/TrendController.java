package com.zemoso.kafkasample.controllers;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.services.interfaces.TrendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/trend/")
public class TrendController {

    @Autowired
    private TrendDataService trendDataService;

    @PostMapping
    public @ResponseBody String postNewTrendMesssage(@RequestBody TrendingData trendingBurst){
        return trendDataService.setTrendData(trendingBurst);
    }

    @GetMapping
    public @ResponseBody List<TrendingData> getRawTrendingData(){
        return trendDataService.getRawTrendingData();
    }

    @GetMapping("/processed/")
    public @ResponseBody List<TrendingData> getProcessedTrendingData() {
        return trendDataService.getProcessedData();
    }

}
