<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    Administrator userData = (Administrator) request.getAttribute("userData");
    ArrayList<Customer> usersTable = (ArrayList<Customer>) request.getAttribute("usersTable");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="text-center col-12">
            <p class="custom-h1" >
                <span>Admin panel</span>
            </p>
        </div>

        <div class="col-12 mt-3">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">Login</th>
                  <th scope="col">Access level</th>
                  <th scope="col">Password</th>
                </tr>
              </thead>
              <tbody>
              <% if (userData != null) { %>
                <tr>
                  <td><%= userData.getLogin() %></td>
                  <td><%= userData.getAccessLevel() %></td>
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