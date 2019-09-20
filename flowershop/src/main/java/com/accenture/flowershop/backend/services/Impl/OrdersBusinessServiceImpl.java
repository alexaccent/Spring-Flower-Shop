package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.dao.FlowerDao;
import com.accenture.flowershop.backend.dao.OrdersDao;
import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.OrdersBusinessService;
import com.accenture.flowershop.exception.OrderCloseException;
import com.accenture.flowershop.exception.OrderCreatedException;
import com.accenture.flowershop.exception.OrderPaymentException;
import com.accenture.flowershop.enums.OrderStatus;
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
    public Orders createOrdersForSession(Customer userData, String[] arrayFlowerId, String[] arrayAmounts) {

        Orders orders = new Orders();
        orders.setCustomer(userData);
        Set<FlowerOrder> ordersFlowersSet = new HashSet<>();

        double priceOrders = 0.0;

        try {
            for (int i = 0; i < arrayFlowerId.length; i++) {
                if (arrayAmounts[i] != null && !arrayAmounts[i].isEmpty()) {

                    Long flowerIdToInt = Long.parseLong(arrayFlowerId[i]);
                    Long flowerAmount = Long.parseLong(arrayAmounts[i]);

                    Flower flowerById = flowerDao.getOne(flowerIdToInt);

                    if (flowerById != null) {

                        BigDecimal priceFlowerOrder = flowerById.getPrice().multiply(new BigDecimal(flowerAmount));
                        priceOrders += priceFlowerOrder.longValue();

                        FlowerOrder flowerOrder = new FlowerOrder();
                        flowerOrder.setOrdersId(orders);
                        flowerOrder.setFlowerId(flowerById);
                        flowerOrder.setAmountFlowers(flowerAmount);
                        flowerOrder.setPrice(priceFlowerOrder);

                        ordersFlowersSet.add(flowerOrder);
                    }
                }
            }

            orders.setFlowerOrders(ordersFlowersSet);
            orders.setPrice(new BigDecimal(priceOrders).setScale(2, RoundingMode.CEILING));

            // Price with discount
            double discount = 1 - ((double) userData.getDiscount() / 100);
            BigDecimal discountPrice = orders.getPrice().multiply(new BigDecimal(discount));
            orders.setDiscountPrice(discountPrice.setScale(2, RoundingMode.CEILING));

        } catch (Exception e) {
            System.out.println(e);
        }

        return orders;
    }

    @Override
    public Orders createOrders(Orders ordersInBasket) throws OrderCreatedException {

        try {
            ordersInBasket.setStatus(OrderStatus.CREATED);
            ordersInBasket.setOrdersDate(new Date());
            ordersDao.add(ordersInBasket);

            return ordersInBasket;
        } catch (Exception ex) {
            throw new OrderCreatedException("Ошибка создания заказа");
        }
    }


    public Orders payOrders(Customer userData, String ordersId) throws OrderPaymentException {

        Long ordersIdLong = Long.parseLong(ordersId);
        Orders orders = ordersDao.getOne(ordersIdLong);
        Customer customerBD = customerDao.getOne(userData.getLogin());

        if (customerBD.getOrders().contains(orders)) {

            if (orders.getStatus().equals(OrderStatus.CREATED)) {

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
                throw new OrderPaymentException("Данный заказ был оплачен");
            }
        } else {
            throw new OrderPaymentException("Неудалось найти заказ");
        }
    }

    public Set<Orders> getOrdersByStatusToUser(User customer, OrderStatus ordersStatus) {

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

    public void closeOrder (String orderId) throws OrderCloseException {
        Long ordersIdLong = Long.parseLong(orderId);
        Orders orderById = ordersDao.getOne(ordersIdLong);

        if (orderById != null && orderById.getStatus().equals(OrderStatus.PAID)) {

            orderById.setStatus(OrderStatus.CLOSED);
            orderById.setOrdersCloseDate(new Date());
            ordersDao.update(orderById);
        } else {
            throw new OrderCloseException("Ошибка закыртия заказа");
        }

    }

    public void closeOrdersAll (String[] arrayOrdersId) throws OrderCloseException {

        for (String ordersId : arrayOrdersId) {

            Long ordersIdLong = Long.parseLong(ordersId);
            Orders orderById = ordersDao.getOne(ordersIdLong);

            if (orderById != null) {

                if (orderById.getStatus().equals(OrderStatus.PAID)) {

                    orderById.setStatus(OrderStatus.CLOSED);
                    orderById.setOrdersCloseDate(new Date());
                    ordersDao.update(orderById);
                }
            } else {
                throw new OrderCloseException("Ошибка закыртия заказов");
            }
        }
    }


    public List<Orders> getOrdersByStatus(OrderStatus ordersStatus) {

        List<Orders> listOrders = ordersDao.getByStatus(ordersStatus);

        return listOrders;
    }

}
