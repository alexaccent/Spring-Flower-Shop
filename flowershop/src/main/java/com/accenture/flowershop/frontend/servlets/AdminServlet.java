package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.Impl.OrdersBusinessServiceImpl;
import com.accenture.flowershop.exception.OrderCloseException;
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
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    // temp
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrdersBusinessServiceImpl ordersService;

    private HttpSession session;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession(false);

        if (session != null) {

            User userData = (User) session.getAttribute("user");

            if (userData != null) {

                if (userData instanceof Administrator) {

                    req.setAttribute("userData", userData);

                    // Customer table
                    List<Customer> usersList = customerDao.getAll();
                    req.setAttribute("usersList", usersList);

                    // Orders table
                    List<Orders> ordersByPaid = ordersService.getOrdersByStatus(OrderStatus.PAID);
                    req.setAttribute("ordersByPaid", ordersByPaid);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("/main");
                }
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession(false);

        if (session != null) {

            User userData = (User) session.getAttribute("user");

            if (userData != null) {

                if (userData instanceof Administrator) {

                    String[] arrayOrderId = req.getParameterValues("order_id");
                    String orderIdOne = req.getParameter("order_one_id");

                    if (arrayOrderId != null) {
                        try {
                            ordersService.closeOrdersAll(arrayOrderId);
                            req.setAttribute("message",  "Заказы успешно закрыты");
                        } catch (OrderCloseException ex) {
                            req.setAttribute("error", ex.getMessage());
                        }
                    }

                    if (orderIdOne != null) {
                        try {
                            ordersService.closeOrder(orderIdOne);
                            req.setAttribute("message",  "Заказ успешно закрыт");
                        } catch (OrderCloseException ex) {
                            req.setAttribute("error", ex.getMessage());
                        }
                    }

                    doGet(req, resp);
                } else {
                    resp.sendRedirect("/main");
                }
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }

    }
}
