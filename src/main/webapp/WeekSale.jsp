<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.grocery.model.Customer"%>
<%@page import="com.grocery.daoimpl.CustomerDaoImpl"%>
<%@page import="com.grocery.model.Order"%>
<%@page import="com.grocery.model.Feature"%>
<%@page import="java.util.List"%>
<%@page import="com.grocery.daoimpl.OrderDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Document</title>

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
 <% OrderDaoImpl obj =new OrderDaoImpl();
List<Feature> sale =obj.weekSale();
double b =obj.weekSales();%>
    <div class="row">
        <div class="col-sm-12 p-3   text-white"> 
          <img src="assets/logo.jpg" alt="" class="float-start">
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-2 ">
  <div class="container-fluid">
    <a class="navbar-brand" href="AdminView.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item p-3">
          <a class="nav-link active" aria-current="page" href="#">Welcome Admin</a>
        </li>
        
        <li class="nav-item p-3">
          <a class="nav-link" href="AdminAllUser.jsp">User</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="Product.jsp">Product</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="Sale.jsp">Sale</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="Orders.jsp">Orders</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link" href="Logout.jsp">Logout</a>
        </li>
        
      </ul>

    </div>
  </div>
</nav>
        </div>
      </div>
       <div class="row">
        <div class="col-sm-12 "> 
        <input type="button" class="btn btn-primary" value="Today sale"
		onclick="window.location='TodaySale.jsp'">
		<input type="button" class="btn btn-primary" value="weekly sale"
		onclick="window.location='WeekSale.jsp'">
          </div>
          </div>
          <div><%
         Date date = new Date();
         out.print( "<h2 align = \"center\">" +date.toString()+"</h2>");
      %>
<h1>Total </h1>
<h1><%out.print(b); %></h1>
<h3><%
String todaydates =null;
String lastdates=null;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
Calendar cal = Calendar.getInstance();
// get starting date
cal.add(Calendar.DAY_OF_YEAR, -8);

// loop adding one day in each iteration
for(int i = 0; i<8; i++){
			    cal.add(Calendar.DAY_OF_YEAR, 1);

   if(i==1)
		   {
	    lastdates =sdf.format(cal.getTime());
	   
   }
    if(i==7)
   {
    	  todaydates =sdf.format(cal.getTime());

   }
    
}
out.print("Sale Between  " +lastdates+" and "+todaydates);


 %></h3></div>
          <div>
          <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Product</th>
      <th scope="col">ProductName</th>
      <th scope="col">UnitPrice</th>
      <th scope="col">Quantity</th>
      <th scope="col">Cost</th>
    </tr>
  </thead>
  <tbody>
  <%for(Feature feature:sale){ %>
<tr>
<td><img alt="" src="assets/<%=feature.getProductImage()%>"width="120" height="80"></td>
<td><%=feature.getProductName() %></td>
<td><%=feature.getPrice()%></td>
<td><%=feature.getQuantity() %></td>
<td><%=feature.getCost() %></td>

</tr>
<%} %>
  </tbody>
</table>
          </div>
          
           
      <div class="footer">
        <p>� 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>