package org.example;

import org.example.converter.MyParser;
import org.example.model.Stub;
import org.example.ws.BaseWsImpl;

import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        BaseWsImpl ws = new BaseWsImpl();
        System.out.println(ws.getGreetings());
        ws.processSomething("Test data send!");
        System.out.println("Sum is: " + ws.sum(2, 3));
        Stub stub = new Stub(7, "Duds", true);
        ws.objectTreatmentReceiving(MyParser.parseObjectToBytes(stub));
        Stub stub1 = (Stub) MyParser.parseBytesToObject(ws.objectTreatmentSending());
        System.out.println(stub1);
        //System.out.println("Hello world!");
    }
}