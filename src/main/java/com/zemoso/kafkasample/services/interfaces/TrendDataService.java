package com.zemoso.kafkasample.services.interfaces;

import com.zemoso.kafkasample.pojos.TrendingData;

import java.util.List;

public interface TrendDataService {
    String setTrendData(TrendingData trendData);
    List<TrendingData> getRawTrendingData();
}
