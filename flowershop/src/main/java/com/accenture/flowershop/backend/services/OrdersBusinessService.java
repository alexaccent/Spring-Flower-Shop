package com.accenture.flowershop.backend.services;

import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.entity.Orders;

import java.util.Map;

public interface OrdersBusinessService {

    /**
     * Created orders in Session
     * @param userData the userData from session user
     * @param arrayFlowerId the arrayFlowerId data from the Flower tables
     * @param arrayAmounts the arrayFlowerId data from the Flower tables
     * @return Orders
     */
    Orders createOrdersForSession(Customer userData, String[] arrayFlowerId, String[] arrayAmounts);

    /**
     * Save orders from session to BD with status the CREATED
     * @param ordersInBasket the ordersInBasket is orders from session
     * @return Orders
     */
    Orders createOrders(Orders ordersInBasket);
}

