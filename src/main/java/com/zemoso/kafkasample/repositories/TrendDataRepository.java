package com.zemoso.kafkasample.repositories;

import com.zemoso.kafkasample.pojos.TrendingData;

import java.util.List;

public interface TrendDataRepository {
    void saveTrendingData(List<TrendingData> trendingDataList);
    List<TrendingData> getTrendingDataList();
}
