package com.zemoso.kafkasample.consumer;

import com.zemoso.kafkasample.pojos.TrendingData;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class TrendDeserializer implements Deserializer<TrendingData> {

    private final static String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public TrendingData deserialize(String topic, byte[] data) {
        try {
            String desrialisedDataString = new String(data, encoding);
            JSONObject deserialisedJson = new JSONObject(desrialisedDataString);
            return new TrendingData(deserialisedJson);
        }catch (UnsupportedEncodingException e){
            throw new SerializationException("Error when deserializing byte[] to string due to unsupported encoding " + encoding);
        }catch (JSONException e){
            System.out.print("Failed to deserialize the string : ");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
