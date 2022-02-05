<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Sign Up</title>
</head>
<style>
body {
	background-image: url('assets/home.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
}

.img-fluid {
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	margin: 30px 550px;
	position: fixed;
	font-size: large;
	color: white;
	
}
</style>
<body class="img-fluid">

	<h3>Sign Up
	</h3>

	<form action="signup" method="post">
        <label for="cname">Enter User Name</label> 
		<input type="text"name="cname" autofocus pattern="[1-9a-zA-Z]{3,}" id="cname"> <br> 
		<label for="pnome">Enter Mobile Number</label> 
        <input type="text" name="pnumber"  required  pattern="[6-9]{1}[0-9]{9}" id="pnome"> <br> 
        <label for="fname">Enter First Name</label> 
        <input type="text" name="fname" required  pattern="[a-zA-Z]{3,}"id="fname"> <br> 
		<label for="lname">Enter Last Name</label> 
        <input type="text" name="lname" required pattern="[a-zA-Z]{3,}" id="lname"> <br> 
        <label for="password"> Set Password</label> 
        <input type="password" name="password" required min="8" id="password"> <br> 
        <label for="eid">E-mail </label> 
        <input type="text" name="eid" pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}"id="eid" required>  <br>
        <label for="address">Enter the Address</label> 
        <input type="text" name="address"required maxlength="128"id="address"> <br> <br> 
        <input type="submit"class="btn btn-success" value="Submit">  <br> <br>
         <c:if test="${not empty erroruserids}">
		<p id="errorcontent"><c:out value="${erroruserids}" /></p>
		</c:if>
    </form> 
    <label>Already User? </label> 
    <input type="button"class="btn btn-primary" value="login in" onclick="window.location='login.jsp'" >

</body>
</html>