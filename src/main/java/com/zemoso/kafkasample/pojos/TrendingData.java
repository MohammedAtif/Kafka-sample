package com.zemoso.kafkasample.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Getter
public class TrendingData implements Serializable {

    private static class ActionType{
        private static final int PLAY = 1;
        private static final int VOTE_UP = 2;
        private static final int VOTE_DOWN = 3;
        private static final int REBURST = 4;
    }

    private Integer id;
    private Integer score = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Range(min = 1, max = 4)
    private Integer actionType;

    public TrendingData() {
    }

    public TrendingData(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActionType(Integer actionType){
        this.actionType = actionType;
        switch (actionType){
            case ActionType.PLAY:{
                score = 10;
                break;
            }case ActionType.REBURST:{
                score = 20;
                break;
            }case ActionType.VOTE_UP:{
                score = 15;
                break;
            }case ActionType.VOTE_DOWN:{
                score = -5;
                break;
            }
        }
        System.out.println("Current score is : "+score);
    }

    public void addTrendingData(TrendingData trendingData){
        this.score += trendingData.score;
    }

    @Override
    public String toString() {
        return "TrendingData{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
