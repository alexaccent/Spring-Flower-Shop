package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.dao.FlowerDao;
import com.accenture.flowershop.backend.dao.OrdersDao;
import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.OrdersBusinessService;
import com.accenture.flowershop.exception.OrderPaymentException;
import com.accenture.flowershop.frontend.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class OrdersBusinessServiceImpl implements OrdersBusinessService {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private FlowerDao flowerDao;

    @Autowired
    private CustomerDao customerDao;

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

        double priceOrders = 0.0;

        for (Map.Entry<Flower, String> orderBySession : ordersInSessions.entrySet()) {
            Flower flower = orderBySession.getKey();
            Long amountFlower = Long.parseLong(orderBySession.getValue());

            BigDecimal priceFlowerOrder = flower.getPrice().multiply(new BigDecimal(amountFlower));

            priceOrders += priceFlowerOrder.longValue();

            FlowerOrder flowerOrder = new FlowerOrder();
            flowerOrder.setOrdersId(orders);
            flowerOrder.setFlowerId(flower);
            flowerOrder.setAmountFlowers(amountFlower);
            flowerOrder.setPrice(priceFlowerOrder);

            ordersFlowersSet.add(flowerOrder);
        }

        orders.setFlowerOrders(ordersFlowersSet);
        orders.setPrice(new BigDecimal(priceOrders).setScale(2, RoundingMode.CEILING));

        double discount = 1 - ((double) userData.getDiscount() / 100);
        BigDecimal discountPrice = orders.getPrice().multiply(new BigDecimal(discount));
        orders.setDiscountPrice(discountPrice.setScale(2, RoundingMode.CEILING));

        ordersDao.add(orders);

        return orders;
    }


    public Orders payOrders(Customer userData, String ordersId) throws OrderPaymentException {

        Long ordersIdLong = Long.parseLong(ordersId);
        Orders orders = ordersDao.getOne(ordersIdLong);
        Customer customerBD = customerDao.getOne(userData.getLogin());

        if (customerBD.getOrders().contains(orders)) {

            Set<FlowerOrder> flowerOrdersSet =  orders.getFlowerOrders();

            //User balance
            userData.getBalance().compareTo(orders.getPrice());

            for (FlowerOrder flowerOrderOne : flowerOrdersSet) {

                Flower flowerById = flowerOrderOne.getFlowerId();

                if (flowerById.getAmount().compareTo(flowerOrderOne.getAmountFlowers()) == -1) {
                    throw new OrderPaymentException("Такого колличества цветов в данный момент нет");
                }
            }

            if (userData.getBalance().compareTo(orders.getDiscountPrice()) == -1) {
                throw new OrderPaymentException("На вашем счете недостаточно средств");
            }

            // Payment
            try {
                for (FlowerOrder flowerOrderOne : flowerOrdersSet) {
                    Flower flowerById = flowerOrderOne.getFlowerId();
                    Long newAmount = flowerById.getAmount() - flowerOrderOne.getAmountFlowers();

                    flowerById.setAmount(newAmount);
                    flowerDao.update(flowerById);
                }

                BigDecimal newBalance = userData.getBalance().subtract(orders.getDiscountPrice());
                userData.setBalance(newBalance.setScale(2, RoundingMode.CEILING));

                orders.setStatus(OrderStatus.PAID);
                orders.setFlowerOrders(flowerOrdersSet);
                orders.setOrdersDate(new Date());

                customerDao.update(userData);
                ordersDao.update(orders);

                return orders;

            } catch (Exception ex) {
                throw new OrderPaymentException("Ошибка оплаты заказа");
            }
        } else {
            throw new OrderPaymentException("Неудалось найти заказ");
        }
    }



    public void closeOrders() {
        this.updateStatus(OrderStatus.CLOSED);
    }

    public Set<Orders> getOrdersByStatusUser (User customer, OrderStatus ordersStatus) {

        Customer customerBD = customerDao.getOne(customer.getLogin());

        Set<Orders> listOrders = customerBD.getOrders();
        Set<Orders> newListOrders = new HashSet<>();

        for (Orders ordersOne : listOrders) {

            if(ordersOne.getStatus().equals(ordersStatus)) {

                newListOrders.add(ordersOne);
            }
        }

        return newListOrders;
    }

    private void updateStatus(OrderStatus ordersStatus) {
        List<Orders> listOrders = ordersDao.getAll();

        for (Orders ordersOne : listOrders) {

            if(ordersOne.getStatus().equals(OrderStatus.CREATED)) {

                ordersOne.setStatus(ordersStatus);
                ordersDao.update(ordersOne);
            }
        }
    }

}
