package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.FlowerDao;
import com.accenture.flowershop.backend.dao.OrdersDao;
import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.entity.FlowerOrder;
import com.accenture.flowershop.backend.entity.Orders;
import com.accenture.flowershop.backend.services.OrdersBusinessService;
import com.accenture.flowershop.frontend.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OrdersBusinessServiceImpl implements OrdersBusinessService {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private FlowerDao flowerDao;


    @Override
    public Map<Flower, String> createOrdersForSession(String[] arrayFlowerId, String[] arrayAmounts) {
        Map<Flower, String> orders = new HashMap<>();

        try {
            for (int i = 0; i < arrayFlowerId.length; i++) {
                if (arrayAmounts[i] != null && !arrayAmounts[i].isEmpty()) {
                    Long flowerIdToInt = Long.parseLong(arrayFlowerId[i]);

                    if (flowerIdToInt >= 0L) {
                        Flower flowerById = flowerDao.getOne(flowerIdToInt);

                        if (flowerById != null) {
                            orders.put(flowerById, arrayAmounts[i]);
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return orders;
    }

    @Override
    public Orders createOrders(Customer userData, Map<Flower, String> ordersInSessions) {

        Customer customer = userData;
        OrderStatus orderStatus = OrderStatus.CREATED;

        Orders orders = new Orders(customer, orderStatus);

        Set<FlowerOrder> ordersFlowersSet = new HashSet<>();

        for (Map.Entry<Flower, String> orderBySession : ordersInSessions.entrySet()) {
            Flower flower = orderBySession.getKey();
            Long amountFlower = Long.parseLong(orderBySession.getValue());

            FlowerOrder flowerOrder = new FlowerOrder();
            flowerOrder.setOrdersId(orders);
            flowerOrder.setFlowerId(flower);
            flowerOrder.setAmountFlowers(amountFlower);

            ordersFlowersSet.add(flowerOrder);
        }

        orders.setFlowerOrders(ordersFlowersSet);
        ordersDao.add(orders);

        return orders;
    }

    @Override
    public Orders payOrders() {
        return null;
    }

    @Override
    public Orders closeOrders() {
        return null;
    }

}
