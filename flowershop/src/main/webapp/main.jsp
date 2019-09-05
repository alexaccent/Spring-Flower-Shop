<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    User userData = (User) request.getAttribute("userData");
    ArrayList<Customer> usersTable = (ArrayList<Customer>) request.getAttribute("usersTable");
    ArrayList<Flower> flowerForTable = (ArrayList<Flower>) request.getAttribute("flowerForTable");
%>
<% if (userData != null) { %>

<div class="container" >

    <div class="row justify-content-center align-items-center">
        <div class="text-center col-12">
            <p class="custom-h1" >
                <span class="text-light bg-dark">Hello</span>
                <span><%= userData.getLogin() %></span>
            </p>
            <p><span class="text-muted custom-h2">Location: Main page</span></p>
        </div>

        <div class="col-12 mt-2">
            <% if(userData instanceof Customer) {
                Customer customerData = (Customer) userData;
            %>
                <table class="table table-striped">
                  <thead>
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
                    <tr>
                      <td><%= customerData.getLogin() %></td>
                      <td><%= customerData.getPhone() %></td>
                      <td><%= customerData.getAddress() %></td>
                      <td><%= customerData.getBalance() %></td>
                      <td><%= customerData.getDiscount() %> %</td>
                      <td><%= customerData.getPassword() %></td>
                    </tr>
                  </tbody>
                </table>
             <% } %>

            <% if (userData instanceof Administrator) {
                Administrator adminData = (Administrator) userData;
            %>
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Login</th>
                      <th scope="col">Password</th>
                      <th scope="col">Access Level</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><%= adminData.getLogin() %></td>
                      <td><%= adminData.getPassword() %></td>
                      <td><%= adminData.getAccessLevel() %></td>
                    </tr>
                  </tbody>
                </table>
             <% } %>
        </div>

        <div class="col-6 text-center mt-5">
          <div class="input-group">
            <div class="input-group-prepend">
              <div class="input-group-text" id="btnGroupAddon">&#10059;</div>
            </div>
            <input type="text" name="search" class="form-control" placeholder="Search" aria-label="Input group example" aria-describedby="btnGroupAddon">
          </div>
        </div>
        <% if(userData instanceof Customer) { %>
            <div class="col-6 text-center mt-5">
                <button type="submit" form="orders" class="btn btn-danger">Оформить Заказ</button>
            </div>
        <% } %>
        <div class="col-12 mb-5">
            <form id="orders" method="POST" action="/main"></form>
            <table class="table table-flowers">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">id</th>
                  <th scope="col">Name</th>
                  <th scope="col">Price</th>
                  <th scope="col">Amount</th>
                  <th scope="col">Image</th>
                  <% if(userData instanceof Customer) { %>
                    <th scope="col">Amount orders</th>
                  <% } %>
                </tr>
              </thead>
              <tbody>
              <% if (flowerForTable != null && !flowerForTable.isEmpty()) { %>
                  <% for (Flower flower : flowerForTable) { %>
                    <tr>
                      <th scope="row"><%= flower.getId() %></th>
                      <td><%= flower.getName() %></td>
                      <td><%= flower.getPrice() %></td>
                      <td><%= flower.getAmount() %></td>
                      <td><%= flower.getImageUrl() %></td>
                      <% if(userData instanceof Customer) { %>
                          <td>
                            <input class="input_amount form-control" type="number" name="amount" min="0" max="<%= flower.getAmount() %>" pattern="^[ 0-9]+$" form="orders">
                            <input type="hidden" name="flower_id" value='<%= flower.getId() %>' form="orders">
                          </td>
                      <% } %>
                    </tr>
                   <% } %>
               <% } %>
              </tbody>
            </table>
        </div>

    </div>
</div>

<% } %>

<%@ include file="layout/footer.jsp" %>