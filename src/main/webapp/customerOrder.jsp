<!DOCTYPE html>
<%@page import="com.chainsys.daoimpl.OrderDaoImpl"%>
<%@page import="com.chainsys.model.Order"%>
<%@page import="com.chainsys.model.Customer"%>
<%@page import="com.chainsys.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.chainsys.daoimpl.ProductDaoImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
    <%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Order</title>
</head>
<style>
.kon
{
  height : 900px;
}
    .float-start
    {
        width: 336px;
    
    }
    .col-sm-3{
    text-align: center;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    }
    .col-sm-12 
    {
        
        background-color: rgb(46,78,93);
    }
    .navbar
    {
      margin-top: 25px;
    }
    .nav-link
    {
      font-family: Verdana, Geneva, Tahoma, sans-serif;
    }
    .footer {
  position: relative;
   margin-top:auto
  left: 0;
  bottom: 0;
  width: 100%;
  height: 50px;
  background-color: rgb(46,78,93);
  color: white;
  text-align: center;
}
</style>

<body>

  
    <div class="row">
        <div class="col-sm-12 p-3   text-white"> 
          <img src="assets/logo.jpg" alt="" class="float-start">
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-2 ">
  <div class="container-fluid">
    <a class="navbar-brand" href="CustomerviewServlet">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
         <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="LoginUserProfileServlet">Profile </a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="CustomerOrderServlet">Order</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="CartServlet">Cart</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Logoutservlet">Logout</a>
        </li>
        
      </ul>
      <form class="d-flex" action="SearchProduct" method="post">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name">
        <button class="btn btn-primary" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
        </div>
      </div>
 <div class= kon>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Order Id</th>
      <th scope="col">Status</th>
      <th scope="col">Order Date</th>
      <th scope="col">Details</th>
    </tr>
  </thead>
  <tbody>
  <c:if test="${not empty orderlist}">
   <c:forEach items="${orderlist}" var="current">
<tr>
<td><c:out value="${current.getOrderid()}" /></td>
<td><c:out value="${current.getStatus()}" /></td>
<td><fmt:formatDate pattern="dd-MM-yyyy" 
         value = "${current.getOrderdate()}" /></td>
<td> <input type="button" value="Details" onclick="window.location='UserOrderFullDetailsServlet?orderId=<c:out value="${current.getOrderid()}" />'" ></td>
</tr>
</c:forEach>
</c:if>
 <c:if test="${empty orderlist}">
   <p>Place Order</p>
</c:if>
  </tbody>
</table>

 </div>
 
      <div class="row">
        <div class="col-sm-12 "> 
        
          </div>
          </div>
      <div class="footer">
        <p>© 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>