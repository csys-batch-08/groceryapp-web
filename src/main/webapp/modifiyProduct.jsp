<%@page import="com.chainsys.model.Product"%>
<%@page import="com.chainsys.daoimpl.ProductDaoImpl"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.chainsys.model.Customer"%>
<%@page import="com.chainsys.daoimpl.CustomerDaoImpl"%>
<%@page import="com.chainsys.model.Order"%>
<%@page import="com.chainsys.dto.Feature"%>
<%@page import="java.util.List"%>
<%@page import="com.chainsys.daoimpl.OrderDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Change Name</title>

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
  position: absolute;
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
        	<input type="button" class="btn btn-primary" value="Add product"
		onclick="window.location='AddProductAdminServlet'">
        <input type="button" class="btn btn-primary" value="Status product"
		onclick="window.location='InActiveProductsServlet'">
	
		<input type="button" class="btn btn-primary" value=" Change Products Price"
		onclick="window.location='ChangeProductPriceAdminServlet'">
		<input type="button" class="btn btn-primary" value="Modify Product Name"
		onclick="window.location='ModifiyProductAdminServlet'">
          </div>
          </div>
          <div id="allusers">

<form action="ModifiyProduct" method="post">
<div class="mb-3">
  <label for="formGroupExampleInput" class="form-label">Enter the product name</label>
  <input type="text" class="form-control" name="pName" id="formGroupExampleInput" placeholder="Product Name" required >
</div>
<div class="mb-3">
  <label for="formGroupExampleInput2" class="form-label">Enter the Product Id</label>
  <input type="text" class="form-control" name="pID" id="formGroupExampleInput2" placeholder="Product Id" name="pID"  required >
</div>
<input type="submit" value="submit">
</form>
</div>
          
          
           
      <div class="footer">
        <p>© 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>