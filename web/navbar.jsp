<%@ page import="don.bitlab.models.User" %><%--
  Created by IntelliJ IDEA.
  User: coldkey
  Date: 27.10.2023
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<%
  User user = (User) request.getSession().getAttribute("currentUser");
%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid" style="color: #3764BF">
    <a class="navbar-brand">A simple chat</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">All posts</a>
        </li>
        <li>
          <a class="nav-item"><%=user.getFullName()%></a>
        </li>
        <form class="d-flex" action="/sign-out" method="post" style="margin-left: 500px">
          <button class="btn btn-dark" type="submit">SIGN OUT</button>
        </form>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
