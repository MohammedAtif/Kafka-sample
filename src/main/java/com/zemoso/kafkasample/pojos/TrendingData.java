package com.zemoso.kafkasample.pojos;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@Data
public class TrendingData implements Serializable {

    private final static String ID = "id";
    private final static String SCORE = "score";

    private Integer id;

    private int score = 1;

    public TrendingData() {
    }

    public TrendingData(JSONObject deserializedObject) {
        this.id = deserializedObject.optInt(ID, 0);
        this.score = deserializedObject.optInt(SCORE, 1);
    }

    public TrendingData(Integer id) {
        this.id = id;
    }

    public void addTrendingData(TrendingData trendingData){
        this.score += trendingData.score;
    }

    public JSONObject convertToJSON(){
        JSONObject serialisedObject = new JSONObject();
        try{
            serialisedObject.put(ID, id);
            serialisedObject.put(SCORE, score);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return serialisedObject;
    }

    @Override
    public String toString() {
        return convertToJSON().toString();
    }
}
