package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.business.UserAccessServiceImpl;
import com.accenture.flowershop.backend.business.interfaces.UserAccessService;
import com.accenture.flowershop.backend.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        String nameJsp = "registration.jsp";

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty() && phone != null && !phone.isEmpty() && address != null && !address.isEmpty()){

            UserAccessService userAccessService = new UserAccessServiceImpl();
            User returnUser = userAccessService.register(login, phone, address, password);

            if (returnUser != null) {

                resp.sendRedirect("/");
            } else {
                String error = "Данный логин уже занят";
                req.setAttribute("error", error);
                RequestDispatcher dispatcher = req.getRequestDispatcher(nameJsp);
                dispatcher.forward(req, resp);
            }
        } else {
            String error = "Пожалуйста заполните все поля формы";
            req.setAttribute("error", error);
            RequestDispatcher dispatcher = req.getRequestDispatcher(nameJsp);
            dispatcher.forward(req, resp);
        }

    }
}
