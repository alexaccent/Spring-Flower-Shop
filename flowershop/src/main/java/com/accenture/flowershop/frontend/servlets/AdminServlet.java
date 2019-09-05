package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.entity.*;
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

    @Autowired
    private CustomerDao customerDao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {

            User userData = (User) session.getAttribute("user");

            if (userData != null) {

                if (userData instanceof Administrator) {

                    req.setAttribute("userData", userData);

                    // Customer table
                    List<Customer> usersForTable = customerDao.getAll();
                    req.setAttribute("usersTable", usersForTable);

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
        doGet(req, resp);
    }
}
