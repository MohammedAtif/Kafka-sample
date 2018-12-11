package com.zemoso.kafkasample.pojos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

@Data
public class TrendingData implements Serializable {

    private final static String ID = "id";
    private final static String SCORE = "score";

    private Integer id;

    private int score = 1;

    public TrendingData() {
    }

    public TrendingData(String deserializeObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TrendingData data = objectMapper.readValue(deserializeObject, this.getClass());
        this.id = data.id;
        this.score = data.score;
    }

    public TrendingData(Integer id) {
        this.id = id;
    }

    public void addTrendingData(TrendingData trendingData){
        this.score += trendingData.score;
    }

    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
