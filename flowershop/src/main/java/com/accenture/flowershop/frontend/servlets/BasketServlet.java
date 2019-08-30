package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.services.Impl.OrdersBusinessServiceImpl;
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
import java.util.Map;

@WebServlet(urlPatterns = "/basket")
public class BasketServlet  extends HttpServlet {

    @Autowired
    private OrdersBusinessServiceImpl ordersService;

    private Map<Flower, String> ordersInSessions;

    private Customer userData;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            userData = (Customer) session.getAttribute("user");

            String[] arrayFlowerId = (String[]) session.getAttribute("arrayFlowerId");
            String[] arrayAmounts = (String[]) session.getAttribute("arrayAmounts");

            ordersInSessions = ordersService.createOrdersForSession(arrayFlowerId, arrayAmounts);

            if (userData != null) {
                req.setAttribute("userData", userData);

                if (ordersInSessions != null) {
                    req.setAttribute("ordersInSessions", ordersInSessions);
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("basket.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (userData != null) {
            String statusOrders = req.getParameter("status_orders").toUpperCase();

            if (statusOrders != null && !statusOrders.isEmpty()) {

                // Created orders
                if (statusOrders.equals(OrderStatus.CREATED.toString())) {

                    if (ordersInSessions != null && !ordersInSessions.isEmpty()) {
                        System.out.println("Create orders. Add orders to bd");

                        ordersService.createOrders(userData, ordersInSessions);
                    }
                }

                // Pay orders
                if (statusOrders.equals(OrderStatus.PAID.toString())) {
                    System.out.println("PAY");
                }
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("basket.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

}
