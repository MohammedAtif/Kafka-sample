package com.zemoso.kafkasample.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TrendingDataMixin {

    @JsonIgnore Integer actionType;

    @JsonCreator
    public TrendingDataMixin(
            @JsonProperty Integer id,
            @JsonProperty Integer score
    ) {}
}
