<%@page import="com.chainsys.model.Customer"%>
<%@page import="com.chainsys.daoimpl.CustomerDaoImpl"%>
<%@page import="com.chainsys.model.Order"%>
<%@page import="com.chainsys.dto.Feature"%>
<%@page import="java.util.List"%>
<%@page import="com.chainsys.daoimpl.OrderDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Orders</title>

<style>
.kon
{
  height : 500px;

}
    .float-start
    {
        width: 336px;
    
    }
    .col-sm-12 
    {
        
        background-color: rgb(46,78,93);
    }
    .navbar
    {
      margin-top: 25px;
      height: 50px;
      width: 900px;
    
    }
    .nav-link
    {
      font-family: Verdana, Geneva, Tahoma, sans-serif;
    }
    .footer {
  position: relative;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 50px;
  margin-top:auto;
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
    <a class="navbar-brand" href="AdminViewServlet">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
        
        <li class="nav-item p-3">
          <a class="nav-link" href="AdminAllUserServlet">User</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="ProductServlet">Product</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="SaleServlet">Sale</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="OrdersServlet">Orders</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="Logoutservlet">Logout</a>
        </li>
        
      </ul>

    </div>
  </div>
</nav>
        </div>
      </div>
 <div class="row">
        <div class="col-sm-12 "> 
        <input type="button" class="btn btn-primary" value="Pending Orders"
		onclick="window.location='PendingOrdersServlet'">
		<input type="button" class="btn btn-primary" value="List of Orders"
		onclick="window.location='ListoforderServlet'">
          </div>
          </div>          
    <c:if test="${not empty OrderList}">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Order Id</th>
      <th scope="col">Status</th>
      <th scope="col">Order Date</th>
      <th scope="col">Accept</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach items="${OrderList}" var="current">
<tr>
<td><c:out value="${current.getOrderid()}" /></td>
<td><c:out value="${current.getStatus()}" /></td>
<td><fmt:formatDate pattern="dd-MM-yyyy"
         value = "${current.getOrderdate()}" /></td>
<td> <input type="button" value="confirm" onclick="window.location='Accept?orderId=<c:out value="${current.getOrderid()}" />'" ></td>
</tr>
</c:forEach>
  </tbody>
</table>
</c:if>
 <c:if test="${empty OrderList}">
   <p>No pending orders</p>
</c:if>
          
          
           
      <div class="footer">
        <p>© 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>