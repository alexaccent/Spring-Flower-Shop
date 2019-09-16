package com.accenture.flowershop.frontend.ws.Impl;

import com.accenture.flowershop.frontend.ws.FlowersStockWebServiceClient;

import javax.xml.ws.WebServiceRef;


public class FlowersStockWebServiceClientImpl implements FlowersStockWebServiceClient {

    static FlowersStockWebServiceImpl service;

    @WebServiceRef(wsdlLocation="http://localhost:8080/ws/FlowersStockWebService?wsdl")
    public void autoIncrement() {
        service.increaseFlowersStockSize(6);
    }
}
