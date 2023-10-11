package org.example.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface BaseWs {
    @WebMethod
    String getGreetings();
    @WebMethod
    void processSomething(String data);
    @WebMethod
    Integer sum(Integer arg0, Integer arg1);
    @WebMethod
    byte[] objectTreatmentSending();
    @WebMethod
    void objectTreatmentReceiving(byte[] obj);
}
