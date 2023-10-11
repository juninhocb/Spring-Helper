package org.example;
import org.example.ws.BaseWsImpl;
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        String addressUrl = "http://localhost:8081/hisoap";
        BaseWsImpl baseWs = new BaseWsImpl();
        Endpoint.publish(addressUrl, baseWs);
        System.out.println("Server started successfully.");
    }
}