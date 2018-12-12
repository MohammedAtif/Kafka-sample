package com.zemoso.kafkasample.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrendingData implements Serializable {

    private final static String ID = "id";
    private final static String SCORE = "score";

    private Integer id;

    private int score = 1;

    public TrendingData() {
    }

    public TrendingData(Integer id) {
        this.id = id;
    }

    public void addTrendingData(TrendingData trendingData){
        this.score += trendingData.score;
    }
}
