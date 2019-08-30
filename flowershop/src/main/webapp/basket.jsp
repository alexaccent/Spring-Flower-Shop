<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.Customer" %>
<%@ page import="com.accenture.flowershop.backend.entity.Flower" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    Customer userData = (Customer) request.getAttribute("userData");
    Map<Flower, String> ordersInSessions = (HashMap<Flower, String>) request.getAttribute("ordersInSessions");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">

        <div class="text-center col-12 mt-5">
            <p class="custom-h1 mb-5">Orders</span></p>
        </div>
        <div class="col-12 mb-5">
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
            <form method="POST" action="/basket">
                <button type="submit" class="btn btn-primary" name="status_orders" value="created">Оформить заказ</button>
            </form>
         <% } else { %>
             <div class="text-center">
                <p>No orders for registration =(</p>
             </div>
         <% } %>
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp" %>