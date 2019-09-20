package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.Impl.*;
import com.accenture.flowershop.exception.OrderPaymentException;
import com.accenture.flowershop.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/basket")
public class BasketServlet  extends HttpServlet {

    @Autowired
    private OrdersBusinessServiceImpl ordersService;

    @Autowired
    private UserBusinessServiceImpl userService;

    private Orders ordersInBasket;

    private  Set<Orders> ordersByCreated;

    private HttpSession session;

    private Customer userData;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession(false);

        if (session != null) {
            User checkUser = (User) session.getAttribute("user");

            if (checkUser instanceof Customer) {

                userData = (Customer) checkUser;

                if (userData != null) {
                    req.setAttribute("userData", userData);

                    String[] arrayFlowerId = (String[]) session.getAttribute("arrayFlowerId");
                    String[] arrayAmounts = (String[]) session.getAttribute("arrayAmounts");

                    // Basket session
                    if (arrayFlowerId != null && arrayAmounts != null) {

                        ordersInBasket = ordersService.createOrdersForSession(userData, arrayFlowerId, arrayAmounts);
                        Set<FlowerOrder> flowerOrdersBasket =  ordersInBasket.getFlowerOrders();

                        req.setAttribute("ordersInBasket", ordersInBasket);
                        req.setAttribute("flowerOrdersBasket", flowerOrdersBasket);

                        session.removeAttribute("arrayFlowerId");
                        session.removeAttribute("arrayAmounts");
                    }

                    // Created orders
                    ordersByCreated = ordersService.getOrdersByStatusToUser(userData, OrderStatus.CREATED);
                    if (ordersByCreated != null && !ordersByCreated.isEmpty()) {
                        req.setAttribute("ordersByCreated", ordersByCreated);
                    }

                    // Paid orders
                    Set<Orders> ordersByPaid = ordersService.getOrdersByStatusToUser(userData, OrderStatus.PAID);
                    Set<Orders> ordersByClosed = ordersService.getOrdersByStatusToUser(userData, OrderStatus.CLOSED);
                    ordersByPaid.addAll(ordersByClosed);
                    if (ordersByPaid != null && !ordersByPaid.isEmpty()) {
                        req.setAttribute("ordersByPaid", ordersByPaid);
                    }

                    RequestDispatcher dispatcher = req.getRequestDispatcher("basket.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                resp.sendRedirect("/main");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        session = req.getSession(false);

        if (userData != null) {
            String statusOrders = req.getParameter("status_orders").toUpperCase();

            if (statusOrders != null && !statusOrders.isEmpty()) {

                // Created orders
                if (statusOrders.equals(OrderStatus.CREATED.toString())) {

                    if (ordersInBasket != null) {
                        System.out.println("Create orders. Add orders to bd");

                        ordersService.createOrders(ordersInBasket);
                        session.removeAttribute("ordersInSessions");
                        session.removeAttribute("ordersByCreated");
                    }
                }

                // Pay orders
                if (statusOrders.equals(OrderStatus.PAID.toString())) {
                    System.out.println("pay orders");

                    String ordersId = req.getParameter("orders_id");

                    if (ordersId != null && !ordersId.isEmpty()) {
                        try {
                            ordersService.payOrders(userData, ordersId);

                            session.removeAttribute("ordersByPaid");
                            Set<Orders> ordersByPaidNew = ordersService.getOrdersByStatusToUser(userData, OrderStatus.PAID);
                            Set<Orders> ordersByClosed = ordersService.getOrdersByStatusToUser(userData, OrderStatus.CLOSED);

                            ordersByPaidNew.addAll(ordersByClosed);
                            req.setAttribute("ordersByPaid", ordersByClosed);

                            String message = "Ваш заказ успешно оплачен";
                            req.setAttribute("message", message);
                        } catch (OrderPaymentException ex) {
                            req.setAttribute("error", ex.getMessage());
                        }
                    }
                }
            }

            doGet(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

}
