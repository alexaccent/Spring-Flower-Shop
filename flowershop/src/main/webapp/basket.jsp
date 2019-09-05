<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    Customer userData = (Customer) request.getAttribute("userData");
    Map<Flower, String> ordersInSessions = (HashMap<Flower, String>) request.getAttribute("ordersInSessions");
    Set<Orders> ordersByCreated = (HashSet<Orders>) request.getAttribute("ordersByCreated");
    Set<Orders> ordersByPaid = (HashSet<Orders>) request.getAttribute("ordersByPaid");


    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="text-center col-12">
            <p class="h1 mb-3">Orders</span></p>
            <% if (error != null && !error.isEmpty()) { %>
                <div class="alert alert-danger col-6 mx-auto">
                  <%
                   out.println(error);
                   request.removeAttribute("error");
                  %>
                </div>
            <% } %>
            <% if (message != null && !message.isEmpty()) { %>
                <div class="alert alert-success col-6 mx-auto">
                  <%
                   out.println(message);
                   request.removeAttribute("message");
                  %>
                </div>
            <% } %>
        </div>
        <div class="col-12 mb-4">
        <p class="text-center h4 text-secondary mb-3">List of not created orders</p>
        <% if(ordersInSessions != null && !ordersInSessions.isEmpty()) { %>
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">Id</th>
                  <th scope="col">Name</th>
                  <th scope="col">Price</th>
                  <th scope="col">Image</th>
                  <th scope="col">Amount</th>
                </tr>
              </thead>
              <tbody>
                  <% for (Map.Entry<Flower, String> order : ordersInSessions.entrySet()) {
                        Flower keyFlower = order.getKey();
                        String amountFlower = order.getValue();
                  %>
                    <tr>
                      <td><%= keyFlower.getId() %></td>
                      <td><%= keyFlower.getName() %></td>
                      <td><%= keyFlower.getPrice() %></td>
                      <td><%= keyFlower.getImageUrl() %></td>
                      <td><%= amountFlower %></td>
                    </tr>
                  <% } %>
              </tbody>
            </table>
            <form method="POST" action="/basket" class="text-center">
                <button type="submit" class="btn btn-primary" name="status_orders" value="created">Оформить заказ</button>
            </form>
             <% } else { %>
                 <div class="text-center">
                    <p>No orders for registration =(</p>
                 </div>
             <% } %>
        </div>

        <!-- Created -->
        <div class="col-12 mb-5">
            <p class="text-center text-primary h4 mb-3">Orders for pay</p>

            <% if(ordersByCreated != null && !ordersByCreated.isEmpty()) { %>
                <% for (Orders orderOne : ordersByCreated) { %>
                    <div class="row">
                        <div class="col-6">
                            Стоимость заказа: <span class="text-danger"><%= orderOne.getPrice() %></span> рублей.
                            <% if(orderOne.getPrice().compareTo(orderOne.getDiscountPrice()) != 0) {%>
                                <p>Стоимость заказа с учетом скидки: <span class="text-danger"><%= orderOne.getDiscountPrice() %></span> рублей.</p>
                            <% } %>
                        </div>
                        <div class="col-6 text-right text-muted">
                             <!-- yyyy.MM.dd G at HH:mm:ss z -->
                            <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm z"); %>
                            Дата создания:
                            <p><%= dateFormat.format(orderOne.getOrdersDate()) %></p>
                        </div>
                    </div>

                    <table class="table">
                      <thead class="thead-dark">
                        <tr>
                          <th scope="col">Name</th>
                          <th scope="col">Amount</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>

                      <% Set<FlowerOrder> ordersFlower = orderOne.getFlowerOrders(); %>
                      <% for (FlowerOrder ordersFlowerOne : ordersFlower) {
                        Flower flowerOne = ordersFlowerOne.getFlowerId();
                      %>
                        <tr>
                          <td><%= flowerOne.getName() %></td>
                          <td><%= ordersFlowerOne.getAmountFlowers() %></td>
                          <td><%= ordersFlowerOne.getPrice() %></td>
                        </tr>
                      <% } %>
                      </tbody>
                    </table>
                    <form method="POST" action="/basket" class="text-center">
                        <input type="hidden" name="orders_id" value="<%= orderOne.getId() %>">
                        <button type="submit" class="btn btn-primary" name="status_orders" value="paid">Оплатить заказ</button>
                    </form>
                <% } %>
            <% } else { %>
                 <div class="text-center">
                    <p>No orders created</p>
                 </div>
            <% } %>
        </div>

        <!-- Paid -->
        <div class="col-12 mb-5">
            <h4 class="text-center text-danger h4 mb-3">Оплаченные заказы</h4>
            <% if(ordersByPaid != null && !ordersByPaid.isEmpty()) { %>

                <% for (Orders orderOne : ordersByPaid) { %>
                    <div class="row">
                        <div class="col-6">
                            <p>Стоимость заказа <span class="text-danger"><%= orderOne.getDiscountPrice() %></span> рублей.</p>
                        </div>
                        <div class="col-6 text-right text-muted">
                            <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm z"); %>
                            <p><%= dateFormat.format(orderOne.getOrdersDate()) %></p>
                        </div>
                    </div>

                    <table class="table">
                      <thead class="thead-dark">
                        <tr>
                          <th scope="col">Name</th>
                          <th scope="col">Amount</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>

                      <% Set<FlowerOrder> ordersFlower = orderOne.getFlowerOrders(); %>
                      <% for (FlowerOrder ordersFlowerOne : ordersFlower) {

                        Flower flowerOne = ordersFlowerOne.getFlowerId();
                      %>
                        <tr>
                          <td><%= flowerOne.getName() %></td>
                          <td><%= ordersFlowerOne.getAmountFlowers() %></td>
                          <td><%= ordersFlowerOne.getPrice() %></td>
                        </tr>
                      <% } %>

                      </tbody>
                    </table>
                <% } %>
             <% } else { %>
                 <div class="text-center">
                    <p>No orders created</p>
                 </div>
             <% } %>
        </div>

    </div>
</div>

<%@ include file="layout/footer.jsp" %>