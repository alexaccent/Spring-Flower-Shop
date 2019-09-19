package com.accenture.flowershop.frontend.ws.Impl;

import com.accenture.flowershop.backend.services.Impl.FlowersBusinessServiceImpl;
import com.accenture.flowershop.frontend.ws.FlowersStockWebService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowersBusinessServiceImpl flowersService;

    @WebMethod
    @Override
    public void increaseFlowersStockSize(@WebParam(name = "count")  int count) {

        flowersService.updateFlowersAmount(count);
        System.out.println("increase FlowersStockSize/ randomNumber: " + count);
    }
}
