package com.zemoso.kafkasample.pojos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class TrendingDataTest {

    @Test
    public void testTrendingData(){
        TrendingData data = new TrendingData(16);
        assertNotNull("Testing if data is not null", data);
    }

}
