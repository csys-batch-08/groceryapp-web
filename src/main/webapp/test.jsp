    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Products</title>
    <script>
        	function msg() {
        		let prs=document.getElementById("prs").value;
        		$.ajax({
        			type:'POST',
        	 url:'updateproduct.jsp',
        	data:'prs='+prs,    
        	cache:false,
        	 success:function(response){
        		 $(".kon").load("updateproduct.jsp");
        		}
        		});
         
        
      };
      </script>

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
  position: sticky;
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
          <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
        
      </ul>

    </div>
  </div>
</nav>
        </div>
      </div>
      <div >
        <div class="kon">
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
          
          <div>
          <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Product</th>
      <th scope="col">ProductName</th>
      <th scope="col">UnitPrice</th>
      <th scope="col">Status</th>
      
    </tr>
  </thead>
  <tbody>
  
  
<tr>
<td><img alt="" src="assets/red-chili-1kg.jpg"width="120" height="80"> </td>

<!-- <td>1</td> -->
<td id= pid>chili 1kg</td>
<td id =prs>500.0</td>
<td> 
  
      
       In stock 
      
      <input type="button" value="Click me" onclick="msg()" >
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/rice-1kg.jpg"width="120" height="80"></td>
<!-- <td>2</td> -->
<td>rice 1kg</td>
<td>20.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/Toor-Dal-1kg.jpg"width="120" height="80"></td>
<!-- <td>3</td> -->
<td>toor 1kg</td>
<td>97.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/Tomato-1kg.jpg"width="120" height="80"></td>
<!-- <td>21</td> -->
<td>tomato 1kg</td>
<td>50.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/ghee-1kg.jpg"width="120" height="80"></td>
<!-- <td>41</td> -->
<td>ghee 1kg</td>
<td>80.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/Peanut-1kg.png"width="120" height="80"></td>
<!-- <td>42</td> -->
<td>Peanut-1kg</td>
<td>119.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/Almond-1kg.jpeg"width="120" height="80"></td>
<!-- <td>43</td> -->
<td>Almond 1kg</td>
<td>60.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/Pista-1kg.jpg"width="120" height="80"></td>
<!-- <td>44</td> -->
<td>	Pista 1kg</td>
<td>2999.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/paste-100g.png"width="120" height="80"></td>
<!-- <td>45</td> -->
<td>tooth paste 100gm</td>
<td>88.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/lemon-1kg.jpg"width="120" height="80"></td>
<!-- <td>61</td> -->
<td>lemon 1kg</td>
<td>60.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

<tr>
<td><img alt="" src="assets/GoldWinner-5-L-Jar-min.png"width="120" height="80"></td>
<!-- <td>81</td> -->
<td>gold winner 5L</td>
<td>1200.0</td>
<td> 
  
      
       In stock 
      
      
 
</td>  
  
</tr>

  </tbody>
</table>
          </div>
        </div>
           
      <div class="footer">
        <p>© 2022  Grocery shop. All rights reserved</p>
        
      </div>
</body>
</html>