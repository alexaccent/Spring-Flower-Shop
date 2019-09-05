package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.Impl.*;
import com.accenture.flowershop.exception.OrderPaymentException;
import com.accenture.flowershop.frontend.enums.OrderStatus;
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

    private Map<Flower, String> ordersInSessions;

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

                        ordersInSessions = ordersService.createOrdersForSession(arrayFlowerId, arrayAmounts);
                        req.setAttribute("ordersInSessions", ordersInSessions);

                        session.removeAttribute("arrayFlowerId");
                        session.removeAttribute("arrayAmounts");
                    }

                    // Created orders
                    ordersByCreated = ordersService.getOrdersByStatusUser(userData, OrderStatus.CREATED);

                    if (ordersByCreated != null && !ordersByCreated.isEmpty()) {
                        req.setAttribute("ordersByCreated", ordersByCreated);
                    }

                    // Paid orders
                    Set<Orders> ordersByPaid = ordersService.getOrdersByStatusUser(userData, OrderStatus.PAID);

                    if (ordersByPaid != null && !ordersByPaid.isEmpty()) {
                        req.setAttribute("ordersByPaid", ordersByPaid);
                    }

                    Set<Orders> ordersCreated = userData.getOrders();

                    if (ordersCreated != null && !ordersCreated.isEmpty()) {
                        req.setAttribute("ordersCreated", ordersCreated);
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

                    if (ordersInSessions != null && !ordersInSessions.isEmpty()) {
                        System.out.println("Create orders. Add orders to bd");
                        ordersService.createOrders(userData, ordersInSessions);

                        session.removeAttribute("ordersInSessions");
                        session.removeAttribute("ordersByCreated");

                        Set<Orders> ordersCreatedNew = ordersService.getOrdersByStatusUser(userData, OrderStatus.CREATED);
                        req.setAttribute("ordersCreated", ordersCreatedNew);
                    }
                }

                // Pay orders
                if (statusOrders.equals(OrderStatus.PAID.toString())) {
                    System.out.println("pay orders");

                    String ordersId = req.getParameter("orders_id");

                    if (ordersId != null && !ordersId.isEmpty()) {
                        try {
                            ordersService.payOrders(userData, ordersId);

                            session.removeAttribute("ordersCreated");
                            Set<Orders> ordersCreatedUpdate = ordersService.getOrdersByStatusUser(userData, OrderStatus.CREATED);
                            req.setAttribute("ordersCreated", ordersCreatedUpdate);

                            session.removeAttribute("ordersByPaid");
                            Set<Orders> ordersByPaidNew = ordersService.getOrdersByStatusUser(userData, OrderStatus.PAID);
                            req.setAttribute("ordersByPaid", ordersByPaidNew);

                            String message = "Ваш заказ успешно оплачен";
                            req.setAttribute("message", message);
                        } catch (OrderPaymentException ex) {
                            req.setAttribute("error", ex.getMessage());
                        }
                    }
                }

                // Logout
                String logout = req.getParameter("logout");
                if (logout != null && !logout.isEmpty() ) {
                    HttpSession session = req.getSession(false);
                    userService.logout(session);
                    resp.sendRedirect("/login");
                }
            }

            doGet(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

}
