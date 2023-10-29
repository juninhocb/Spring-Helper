
package com.example.springsoapclient.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PersonPort", targetNamespace = "http://localhost:8080/hisoap")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PersonPort {


    /**
     * 
     * @param getPersonRequest
     * @return
     *     returns com.example.springsoapclient.ws.GetPersonResponse
     */
    @WebMethod
    @WebResult(name = "getPersonResponse", targetNamespace = "http://localhost:8080/hisoap", partName = "getPersonResponse")
    public GetPersonResponse getPerson(
        @WebParam(name = "getPersonRequest", targetNamespace = "http://localhost:8080/hisoap", partName = "getPersonRequest")
        GetPersonRequest getPersonRequest);

}