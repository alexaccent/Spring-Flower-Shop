package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.services.Impl.UserBusinessServiceImpl;
import com.accenture.flowershop.backend.services.Impl.UserMarshgallingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Autowired
    private UserBusinessServiceImpl userServicesRegistration;

    @Autowired
    private UserMarshgallingServiceImp userMarshgallingService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        String nameJsp = "registration.jsp";
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty() && phone != null && !phone.isEmpty() && address != null && !address.isEmpty()){

            Customer returnUser = (Customer) userServicesRegistration.register(login, password, phone, address);
            userMarshgallingService.convertFromObjectToXML(returnUser, userMarshgallingService.getPath() + returnUser.getLogin() + ".xml");

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
