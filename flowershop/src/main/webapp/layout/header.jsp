<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.accenture.flowershop.backend.entity.*" %>
<%
    String uri = (String) request.getRequestURI().toString();
    User userData = (User) request.getAttribute("userData");

    String registrNameUrl;
%>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/styles.css" />

    <script src="static/js/lib/jquery-3.4.1.min.js"></script>

    <script src="static/js/lib/jquery.mask.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body class="d-flex flex-column h-100">
<main class="flex-shrink-0">
    <nav class="site-header sticky-top py-1 mb-5">
      <div class="container d-flex flex-column flex-md-row justify-content-between">

        <a class="py-3 d-none d-md-inline-block logo" href="/main">Spring Flower Shop</a>

        <% if (userData != null) { %>
            <a class="py-3 d-none d-md-inline-block" href="/main">Главная</a>
        <% } %>

        <% if (userData != null && userData instanceof Administrator) { %>
            <a class="py-3 d-none d-md-inline-block " href="/admin">Админ панель</a>
        <% } %>

        <% if (userData != null && userData instanceof Customer) { %>
            <a class="py-3 d-none d-md-inline-block " href="/basket">Страница заказов</a>
        <% } %>

        <% if (userData != null) { %>
            <a class="py-3 d-none d-md-inline-block" href="/registration">Добавить пользователя</a>
        <% } %>

        <% if (userData != null) { %>
            <form class="py-2 mb-0 d-none d-md-inline-block"  method="POST" action="/main">
                <button type="submit" name="logout" value="ok" class="btn btn-danger">Выйти</button>
            </form>
        <% } else { %>
            <span class="py-3 d-none d-md-inline-block"></span>
        <% } %>

      </div>
    </nav>