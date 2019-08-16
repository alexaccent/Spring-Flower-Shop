package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.business.UserAccessServiceImpl;
import com.accenture.flowershop.backend.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {

            User userData = (User) session.getAttribute("user");

            if (userData != null) {
                req.setAttribute("userData", userData);

                // Users table
                Map<String, User> usersForTable = UserAccessServiceImpl.getUsersStorage();
                req.setAttribute("usersTable", usersForTable);

                RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
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
        String logout = req.getParameter("logout");
        System.out.println(logout);

        if (logout != null && !logout.isEmpty() ) {
            System.out.println("test1");
            HttpSession session = req.getSession(false);

            if (session != null) {
                session.removeAttribute("user");
                System.out.println("test2");
                resp.sendRedirect("/login");
            } else {
                resp.sendRedirect("/login");
            }

        }
    }
}
