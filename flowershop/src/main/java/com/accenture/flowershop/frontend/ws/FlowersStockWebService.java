package com.accenture.flowershop.frontend.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;

public interface FlowersStockWebService {

    /**
     * Method add random 0-10 to amount  flowers
     * when get url - http://localhost:8080/ws/FlowersStockWebService?wsdl
     * */
    public void increaseFlowersStockSize(int count);
}
