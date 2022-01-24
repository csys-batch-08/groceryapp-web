<!DOCTYPE html>
<%@page import="com.grocery.daoimpl.OrderDaoImpl"%>
<%@page import="com.grocery.model.Order"%>
<%@page import="com.grocery.model.Feature"%>
<%@page import="com.grocery.model.Customer"%>
<%@page import="com.grocery.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.grocery.daoimpl.ProductDaoImpl"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Order Details</title>
</head>
<style>
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
<% Customer customer = (Customer) session.getAttribute("logincustomer");%>
    <div class="row">
        <div class="col-sm-12 p-3   text-white"> 
          <img src="assets/logo.jpg" alt="" class="float-start">
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-2 ">
  <div class="container-fluid">
    <a class="navbar-brand" href="CustomerView.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="LoginUserProfile.jsp"><% out.print("Welcome, " + customer.getFirstName() + "!"); %> </a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="CustomerOrder.jsp">Order</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Cart.jsp">Cart</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Logout.jsp">Logout</a>
        </li>
        
      </ul>
      <form class="d-flex" action="SearchProduct">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name">
        <button class="btn btn-primary" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 "> 
        <input type="button" class="btn btn-primary" value="Back"
		onclick="window.location='CustomerOrder.jsp'">
          </div>
          </div>
       <div class="row">
        <% int b=Integer.parseInt(request.getParameter("orderId"));
Feature feature= new Feature();
feature.setOrderId(b);
Order str =new Order();
str.setOrderid(b);
OrderDaoImpl
obj =new OrderDaoImpl ();
List<Feature> orderlist=obj.userOrderDetails(feature);
String status = obj.status(str);
double c=obj.userOrderDetailse(feature);
%>
<div class="table-responsive">
<table class="table">
<thead>
<tr>
<th>Product</th>
<th>ProductName</th>
<th>Price</th>
<th>Quantity</th>
<th>Cost</th>
</tr>
</thead>
<tbody>
<%for(Feature orders:orderlist){ %>
<tr>
<td><img alt="" src="assets/<%=orders.getProductImage()%>"width="120" height="80"></td>
<td><%=orders.getProductName()%></td>
<td><%=orders.getPrice()%></td>
<td><%=orders.getQuantity()%></td>
<td><%=orders.getCost()%></td>



</tr>
<%} %>
</tbody>
</table>
<h3><% out.print("Total ="+c); %></h3>
<% 
if(status.equalsIgnoreCase("confirm"))
{%>

 <input type="button" value="cancel order" onclick="window.location='CancelOrder?orderId=<%=b%>'" >
<% }%>
          </div>
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