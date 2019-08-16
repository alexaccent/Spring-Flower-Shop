<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.User" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    User userData = (User) request.getAttribute("userData");
    Map<String, User> usersTable = (HashMap<String, User>) request.getAttribute("usersTable");
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
        <div class="col-12">
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">id</th>
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
                  <th scope="row"><%= userData.getId() %></th>
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
                  <th scope="col">id</th>
                  <th scope="col">Login</th>
                  <th scope="col">Phone</th>
                  <th scope="col">Address</th>
                  <th scope="col">Balance</th>
                  <th scope="col">Discount</th>
                  <th scope="col">Password</th>
                </tr>
              </thead>
              <tbody>
              <% if(usersTable != null && !usersTable.isEmpty()) { %>
                  <% for (Map.Entry<String, User> user : usersTable.entrySet()) { %>
                    <% User userVal = user.getValue(); %>
                    <tr>
                      <th scope="row"><%= userVal.getId() %></th>
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