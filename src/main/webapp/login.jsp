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

<title>Login</title>
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
	margin: 200px 550px;
	position: fixed;
	font-size: large;
	color: white;
}
</style>
<body class="img-fluid">

	<h3>Grocery App</h3>

	<form action="login" method="post">
		<label for="pnumber">Mobile Number</label><br> <input type="text"
			name="phonenumber" required autofocus pattern="[6-9]{1}[0-9]{9}"
			placeholder="Enter Mobile number" id="pnumber"> <br>
		<br> <label for="pword">Password</label><br> <input
			type="password" name="pword" required min="8"
			placeholder="Enter password" id="pword"> <br> <br> <input
			type="submit" class="btn btn-success" value="Login"><br>
		<br>
		

 <c:if test="${not empty erroruserid}">
		<p id="errorcontent"><c:out value="${erroruserid}" /></p>
		</c:if>
		
	</form>
	<label>New User? </label>
	<input type="button" class="btn btn-primary" value="Sign Up"
		onclick="window.location='signup.jsp'">
</body>
</html>