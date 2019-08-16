package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.business.UserAccessServiceImpl;
import com.accenture.flowershop.backend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = 	LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String nameJsp = "login.jsp";
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty() ) {
            UserAccessServiceImpl userAccessService = new UserAccessServiceImpl();
            User userData = userAccessService.login(login, password);

            if (userData != null) {

                HttpSession session = req.getSession(true);
                session.setAttribute("user", userData);
                session.setMaxInactiveInterval(30*60);

                resp.sendRedirect("/main");
            } else {
                String error = "Вы ввели не верный логин или пароль";
                req.setAttribute("error", error);

                RequestDispatcher dispatcher = req.getRequestDispatcher(nameJsp);
                dispatcher.forward(req, resp);
            }
        } else {
            String error = "Не все поля заполнены";
            req.setAttribute("error", error);

            RequestDispatcher dispatcher = req.getRequestDispatcher(nameJsp);
            dispatcher.forward(req, resp);
        }

    }
}
