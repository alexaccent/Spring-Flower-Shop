<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.Customer" %>
<%@ page import="com.accenture.flowershop.backend.entity.Flower" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    Customer userData = (Customer) request.getAttribute("userData");
    ArrayList<Customer> usersTable = (ArrayList<Customer>) request.getAttribute("usersTable");
    ArrayList<Flower> flowerForTable = (ArrayList<Flower>) request.getAttribute("flowerForTable");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="text-center col-12">
            <p class="custom-h1" >
                <span class="text-light bg-dark">Hello</span>
                <% if (userData != null) { %>
                    <span><%= userData.getLogin() %></span>
                <% } %>
            </p>
            <p><span class="text-muted custom-h2">Location: Main page</span></p>
        </div>
        <div class="col-6 text-center">
          <div class="input-group">
            <div class="input-group-prepend">
              <div class="input-group-text" id="btnGroupAddon">&#10059;</div>
            </div>
            <input type="text" name="search" class="form-control" placeholder="Search" aria-label="Input group example" aria-describedby="btnGroupAddon">
          </div>
        </div>
        <div class="col-6 text-center">
            <button type="submit" form="orders" class="btn btn-danger">Оформить Заказ</button>
        </div>

        <div class="col-12">
            <form id="orders" method="POST" action="/main"></form>
            <table class="table table-flowers">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">id</th>
                  <th scope="col">Name</th>
                  <th scope="col">Price</th>
                  <th scope="col">Amount</th>
                  <th scope="col">Image</th>
                  <th scope="col">Amount</th>
                </tr>
              </thead>
              <tbody>
              <% if(flowerForTable != null && !flowerForTable.isEmpty()) { %>
                  <% for (Flower flower : flowerForTable) { %>
                    <tr>
                      <th scope="row"><%= flower.getId() %></th>
                      <td><%= flower.getName() %></td>
                      <td><%= flower.getPrice() %></td>
                      <td><%= flower.getAmount() %></td>
                      <td><%= flower.getImageUrl() %></td>
                      <td>
                        <input class="input_amount" type="number" name="amount" pattern="^[ 0-9]+$" form="orders">
                        <input type="hidden" name="flower_id" value='<%= flower.getId() %>' form="orders">
                      </td>
                    </tr>
                   <% } %>
               <% } %>
              </tbody>
            </table>
        </div>

        <div class="col-12 mt-5">
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
              <% if (userData != null) { %>
                <tr>
                  <td><%= userData.getLogin() %></td>
                  <td><%= userData.getPhone() %></td>
                  <td><%= userData.getAddress() %></td>
                  <td><%= userData.getBalance() %></td>
                  <td><%= userData.getDiscount() %> %</td>
                  <td><%= userData.getPassword() %></td>
                </tr>
               <% } %>
              </tbody>
            </table>
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
              <% if (usersTable != null && !usersTable.isEmpty()) { %>
                  <% for (Customer userVal : usersTable) { %>
                    <tr>
                      <td><%= userVal.getLogin() %></td>
                      <td><%= userVal.getPhone() %></td>
                      <td><%= userVal.getAddress() %></td>
                      <td><%= userData.getBalance() %></td>
                      <td><%= userData.getDiscount() %> %</td>
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