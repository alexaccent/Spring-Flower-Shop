<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%@ page import="com.accenture.flowershop.enums.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    List<Customer> usersList = (ArrayList<Customer>) request.getAttribute("usersList");
    List<Orders> ordersByPaid = (ArrayList<Orders>) request.getAttribute("ordersByPaid");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="text-center col-12">
            <p class="custom-h1" >
                <span>Admin panel</span>
            </p>
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
        <div class="col-12 mt-4">
            <table class="table table-striped mb-0">
              <thead>
                <tr>
                  <th scope="col">Login</th>
                  <th scope="col">Access level</th>
                  <th scope="col">Password</th>
                </tr>
              </thead>
              <tbody>
              <% if (userData instanceof Administrator) {
                Administrator adminData = (Administrator) userData;
              %>
                <tr>
                  <td><%= adminData.getLogin() %></td>
                  <td><%= adminData.getAccessLevel() %></td>
                  <td><%= adminData.getPassword() %></td>
                </tr>
               <% } %>
              </tbody>
            </table>
        </div>

        <!-- Orders paid -->
        <div class="col-12 mt-5">
            <p class="text-center text-primary h4 mb-3">Оплаченные заказы</p>
            <form id="orders" method="POST" action="/admin"></form>

            <% if(ordersByPaid != null && !ordersByPaid.isEmpty()) { %>

                <table class="table">
                  <thead class="thead-dark">
                    <tr>
                      <th scope="col">Id</th>
                      <th scope="col">Login customer</th>
                      <th scope="col">Status</th>
                      <th scope="col">Discount price</th>
                      <th scope="col">Price</th>
                      <th scope="col">Date</th>
                      <th scope="col">Close Date</th>
                      <th scope="col">Act</th>
                    </tr>
                  </thead>
                  <tbody>
                   <% for (Orders orderOne : ordersByPaid) { %>
                    <tr>
                      <td class="align-middle"><%= orderOne.getId() %></td>
                      <td class="align-middle"><%= orderOne.getCustomer().getLogin() %></td>
                      <td class="align-middle"><%= orderOne.getStatus() %></td>
                      <td class="align-middle"><%= orderOne.getDiscountPrice() %></td>
                      <td class="align-middle"><%= orderOne.getPrice() %></td>
                      <td class="align-middle"><%= dateFormat.format(orderOne.getOrdersDate()) %></td>
                      <td class="align-middle">
                        <% if (orderOne.getOrdersCloseDate() != null) { %>
                           <%= dateFormat.format(orderOne.getOrdersCloseDate()) %>
                        <% } %>
                      </td>
                      <td class="align-middle">
                      <input type="hidden" name="order_id" value='<%= orderOne.getId() %>' form="orders">
                      <% if (orderOne.getStatus().equals(OrderStatus.PAID)) { %>
                          <form id="orders_one" method="POST" action="/admin">
                            <button type="submit" class="btn btn-secondary" name="order_one_id" value='<%= orderOne.getId() %>'>Закрыть</button>
                          </form>
                      <% }  else { %>
                            <span>No Act</span>
                      <% } %>
                      </td>
                    </tr>
                  <% } %>
                  </tbody>
                </table>
                <div class="text-center" >
                    <button type="submit" class="btn btn-secondary" form="orders">Закрыть все</button>
                </div>
            <% } else { %>
                 <div class="text-center">
                    <p>Нет оплаченных заказов</p>
                 </div>
            <% } %>
        </div>

        <div class="text-center col-12 mt-5">
            <p class="custom-h2 mb-0"><span class="text-dark">Information for Administration</span></p>
            <p class="text-muted">If you User,<span class="text-danger"> Close your eyes!</span></p>
        </div>
        <div class="col-12 mb-5">
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">Login</th>
                  <th scope="col">Phone</th>
                  <th scope="col">Address</th>
                  <th scope="col">Balance</th>
                  <th scope="col">Discount</th>
                  <th scope="col">Password</th>
                </tr>
              </thead>
              <tbody>
              <% if (usersList != null && !usersList.isEmpty()) { %>
                  <% for (Customer userVal : usersList) { %>
                    <tr>
                      <td><%= userVal.getLogin() %></td>
                      <td><%= userVal.getPhone() %></td>
                      <td><%= userVal.getAddress() %></td>
                      <td><%= userVal.getBalance() %></td>
                      <td><%= userVal.getDiscount() %></td>
                      <td><%= userVal.getPassword() %></td>
                    </tr>
                  <% } %>
               <% } %>
              </tbody>
            </table>
        </div>

    </div>
</div>

<%@ include file="layout/footer.jsp" %>