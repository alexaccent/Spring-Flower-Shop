package com.accenture.flowershop.frontend.servlets;
import com.accenture.flowershop.backend.entity.Administrator;
import com.accenture.flowershop.backend.entity.User;
import com.accenture.flowershop.backend.services.Impl.UserBusinessServiceImpl;
import com.accenture.flowershop.exception.UserLoginException;
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

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessServiceImpl userServicesLogin;

    // private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String nameJsp = "login.jsp";

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty() ) {

            try {

                User userData = userServicesLogin.login(login, password);

                HttpSession session = req.getSession(true);
                session.setAttribute("user", userData);
                session.setMaxInactiveInterval(30*60);

                if (userData instanceof Administrator) {
                    resp.sendRedirect("/admin");
                } else {
                    resp.sendRedirect("/main");
                }

            } catch (UserLoginException ex) {

                req.setAttribute("error", ex.getMessage());
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
