package org.example.ws;

import org.example.converter.MyParser;
import org.example.model.Stub;

import javax.jws.WebService;

@WebService(endpointInterface = "org.example.ws.BaseWs")
public class BaseWsImpl implements BaseWs{
    @Override
    public String getGreetings() {
        return "Hello World!";
    }
    @Override
    public void processSomething(String data) {
        System.out.println("Data process: " + data);
    }
    @Override
    public Integer sum(Integer arg0, Integer arg1) {
        return arg0 + arg1;
    }
    @Override
    public byte[] objectTreatmentSending() {
        Stub stub = new Stub(1, "Foo", true);
        return MyParser.parseObjectToBytes(stub);
    }
    @Override
    public void objectTreatmentReceiving(byte[] obj) {
        Stub stub = (Stub) MyParser.parseBytesToObject(obj);
        System.out.println(stub);
    }
}
