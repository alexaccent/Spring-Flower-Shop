<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.String" %>
<%@ include file="layout/header.jsp" %>
<%
    String error = (String) request.getAttribute("error");
%>

<div class="container" >
    <div class="row justify-content-center align-items-center">
        <div class="col-6">
            <div class="text-center">
                <h2>Вход</h2>
            </div>
            <% if (error != null && !error.isEmpty()) { %>
                <div class="alert alert-danger" role="alert">
                  <%
                   out.println(error);
                   request.removeAttribute("error");
                  %>
                </div>
            <% } %>
            <form method="POST" action="/" >
              <div class="form-group">
                <label for="login">Логин</label>
                <input type="text" name="login" class="form-control" id="login" placeholder="Login">
              </div>
              <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
              </div>
              <button type="submit" class="btn btn-primary">Войти</button>
              <a class="ml-5" href="/registration">Регистрация</a>
            </form>
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp" %>
