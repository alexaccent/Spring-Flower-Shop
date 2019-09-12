package com.accenture.flowershop.frontend.ws.Impl;

import com.accenture.flowershop.frontend.ws.FlowersStockWebServiceClient;

import javax.xml.ws.WebServiceRef;


public class FlowersStockWebServiceClientImpl implements FlowersStockWebServiceClient {

    @WebServiceRef(wsdlLocation="http://localhost:8080/ws/FlowersStockWebService?wsdl")
    static FlowersStockWebServiceImpl service;

    public static void main(String[] args) {
        service.increaseFlowersStockSize(6);
    }
}
