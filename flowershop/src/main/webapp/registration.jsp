<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*" %>
<%@ include file="layout/header.jsp" %>
<%
    String error = (String) request.getAttribute("error");
%>

<script src="static/js/main.js"></script>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="col-6">
            <div class="text-center">
                <h2>Регистрация</h2>
            </div>
            <div>
            <% if (error != null && !error.isEmpty()) { %>
                <div class="alert alert-danger" role="alert">
                  <%
                   out.println(error);
                   request.removeAttribute("error");
                  %>
                </div>
            <% } %>
            <form method="POST" action="/registration" class="mb-5">
              <div class="form-group">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" class="form-control" id="login" placeholder="Login">
              </div>
              <div class="form-group">
                <label for="phone">Телефон</label>
                <input type="tel" name="phone" class="form-control" id="phone" placeholder="Phone">
              </div>
              <div class="form-group">
                <label for="address">Адрес</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Address">
              </div>
              <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="password">Повторите пароль</label>
                <input type="password" name="check_password" class="form-control" id="check_password" placeholder="Password">
              </div>
              <button type="submit" id="button-reg" class="btn btn-primary" >Регистрация</button>

              <% if (userData == null) { %>
                <a class="ml-5" href="/login">Войти</a>
              <% } %>

            </form>
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp" %>
