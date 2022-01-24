<!DOCTYPE html>
<%@page import="com.chainsys.daoimpl.CartDaoImpl"%>
<%@page import="com.chainsys.daoimpl.OrderDaoImpl"%>
<%@page import="com.chainsys.model.Order"%>
<%@page import="com.chainsys.model.Feature"%>
<%@page import="com.chainsys.model.Customer"%>
<%@page import="com.chainsys.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.chainsys.daoimpl.ProductDaoImpl"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Cart</title>
</head>
<style>
.kon
{
  height : 700px;

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
          <a class="nav-link active" aria-current="page" href="LoginUserProfile.jsp">Profile </a>
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
 <div class=kon>
  <% 
int oid = 0;


 
	int cid=customer.getCustomerid();
	Order order = new Order();
	order.setCustomerid(cid);
	OrderDaoImpl obj1 = new OrderDaoImpl();
	int ojid = obj1.cartCheck(order);
	if(ojid>0)
	{
		oid=ojid;
	}  
	session.setAttribute("logincustomerorderId", oid); 
	double offer=0;
%>
<% Feature feature = new Feature();
feature.setOrderId(oid);
CartDaoImpl obj =new CartDaoImpl();
List<Feature> cartlist= obj.showCartin(feature);
double total= obj.showCartinTotal(feature);%>
<div class="table-responsive">
  <table class="table">
<thead>
<tr>
<th>Product </th>
<th>Product Name</th>
<th>Price</th>
<th>Quantity</th>
<th>Total</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<%for(Feature list: cartlist){ %>
<tr>
<td><img alt="" src="assets/<%=list.getProductImage()%>"width="120" height="80"></td>
<td><%=list.getProductName()%></td>
<td><%=list.getPrice() %></td>
<td><input type="button" value="-" onclick="window.location='DecreaseQuantity?pId=<%=list.getProductId()%>'" ><%=list.getQuantity()%>
<input type="button" value="+" onclick="window.location='IncreaseQuantity?pId=<%=list.getProductId()%>'" ></td>
<td><%=list.getCost() %></td>
<td><input type="button" value="Remove" onclick="window.location='Deleteproductincart?pId=<%=list.getProductId()%>'" ></td>
</tr>
<%} %>
</tbody>
</table><br>
Total  Price = <% out.print( total) ;%>/-<br>
<%if(total>499&& total<999)
	{
	offer=total*0.05;
	total= total-offer;
	 out.print("offer price="+ total+"/-") ;
	}
	else if(total>=999)
	{
		offer=total*0.1;
		total= total-offer;
		out.print("offer price="+ total+"/-") ;
	}%>
	<%if(!cartlist.isEmpty()) {%>
<h4><input type="button" value="Place the order" onclick="window.location='ConformOrder'" ></h4>
<%} %>
</div>
 </div>
      <div class="footer">
        <p>© 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>