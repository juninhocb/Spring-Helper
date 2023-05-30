package com.carlosjr.mosquitto;


import com.carlosjr.mosquitto.config.MosquittoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PublishOnBrokerTest {
    @Autowired
    private MosquittoConfig.MyGateway myGateway;
    @Test
    public void publishTester(){
        myGateway.sendToMqtt("Publishing on broker. ");
    }
}
