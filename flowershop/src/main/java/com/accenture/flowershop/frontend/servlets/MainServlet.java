package com.accenture.flowershop.frontend.servlets;

import com.accenture.flowershop.backend.entity.*;
import com.accenture.flowershop.backend.services.Impl.FlowersBusinessServiceImpl;
import com.accenture.flowershop.backend.services.Impl.UserBusinessServiceImpl;
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

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    @Autowired
    private UserBusinessServiceImpl userService;

    @Autowired
    private FlowersBusinessServiceImpl flowersService;

    private List<Flower> flowerForTable;

    private String priceMinInSearch;
    private String priceMaxInSearch;

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

                req.setAttribute("userData", userData);

                String search = req.getParameter("search");
                priceMinInSearch = req.getParameter("price_min");
                priceMaxInSearch = req.getParameter("price_max");

                // Flowers table
                if (search != null && !search.isEmpty()) {

                    flowerForTable = flowersService.searchFlowers(search);
                    session.removeAttribute("flowerForTable");
                    req.setAttribute("flowerForTable", flowerForTable);
                } else if (priceMinInSearch != null && priceMaxInSearch != null) {

                    req.setAttribute("priceMinInSearch", priceMinInSearch);
                    req.setAttribute("priceMaxInSearch", priceMaxInSearch);

                    flowerForTable = flowersService.searchMinAndMaxPrice(priceMinInSearch, priceMaxInSearch);
                    session.removeAttribute("flowerForTable");
                    req.setAttribute("flowerForTable", flowerForTable);
                } else {

                    flowerForTable = flowersService.getAllFlowers();
                    session.removeAttribute("flowerForTable");
                    req.setAttribute("flowerForTable", flowerForTable);
                }

                // Customer table
                List<Customer> usersForTable = userService.getListForTable();
                req.setAttribute("usersTable", usersForTable);

                if (!flowerForTable.isEmpty()) {
                    session.removeAttribute("flowerForTable");
                    req.setAttribute("flowerForTable", flowerForTable);
                }

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
        req.setCharacterEncoding("UTF-8");

        String[] arrayFlowerId = req.getParameterValues("flower_id");
        String[] arrayAmounts = req.getParameterValues("amount");

        if (arrayFlowerId != null && arrayFlowerId.length > 0 ) {

            HttpSession session = req.getSession(true);
            session.setAttribute("arrayFlowerId", arrayFlowerId);
            session.setAttribute("arrayAmounts", arrayAmounts);
            session.setMaxInactiveInterval(30*60);

            resp.sendRedirect("/basket");
        }

        // Logout
        String logout = req.getParameter("logout");

        if (logout != null && !logout.isEmpty() ) {

            HttpSession session = req.getSession(false);

            if (session != null) {
                session.removeAttribute("user");
                session.removeAttribute("ordersInSessions");
                resp.sendRedirect("/login");
            } else {
                resp.sendRedirect("/login");
            }
        }
    }
}
