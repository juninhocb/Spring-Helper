package org.example.ws;

import javax.jws.WebService;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;


@WebService(endpointInterface = "org.example.ws.BaseWs")
public class BaseWsImpl implements BaseWs{

    private final BaseWs webService;

    public BaseWsImpl() throws MalformedURLException {
        String linkWs = "http://localhost:8081/hisoap?wsdl";
        URL url = new URL(linkWs);
        String serviceQualifierName = "http://ws.example.org/";
        //qualified name
        // take from this  targetNamespace="http://ws.example.org/" name="BaseWsImplService"
        QName qName = new QName(serviceQualifierName, "BaseWsImplService");
        Service service = Service.create(url, qName);
        webService = service.getPort(BaseWs.class);
    }

    @Override
    public String getGreetings() {
        return webService.getGreetings();
    }
    @Override
    public void processSomething(String data) {
        webService.processSomething(data);
    }
    @Override
    public Integer sum(Integer arg0, Integer arg1) {
        return webService.sum(arg0, arg1);
    }
    @Override
    public byte[] objectTreatmentSending() {
        return webService.objectTreatmentSending();
    }
    @Override
    public void objectTreatmentReceiving(byte[] obj) {
        webService.objectTreatmentReceiving(obj);
    }
}
