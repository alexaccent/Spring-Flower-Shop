<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String uri = (String) request.getRequestURI().toString(); %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/styles.css" />
</head>
<body class="d-flex flex-column h-100">
<main class="flex-shrink-0">
    <nav class="site-header sticky-top py-1 mb-5">
      <div class="container d-flex flex-column flex-md-row justify-content-between">
        <a class="py-3 d-none d-md-inline-block logo" href="/main">Spring Flower Shop</a>
        <a class="py-3 d-none d-md-inline-block" href="/registration">Регистрация</a>
        <a class="py-3 d-none d-md-inline-block " href="/basket">Basket</a>
        <% if (uri.equals("/main.jsp") || uri.equals("/basket.jsp")) { %>
            <form class="py-2 mb-0 d-none d-md-inline-block"  method="POST" action="/main">
                <button type="submit" name="logout" value="ok" class="btn btn-danger">Выйти</button>
            </form>
        <% } else { %>
            <span class="py-3 d-none d-md-inline-block"></span>
        <% } %>
      </div>
    </nav>