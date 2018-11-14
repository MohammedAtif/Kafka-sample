package com.zemoso.kafkasample.pojos;

import lombok.Data;

@Data
public class TrendingData {

    private Integer id;

    public TrendingData() {
    }

    public TrendingData(Integer id) {
        this.id = id;
    }
}
