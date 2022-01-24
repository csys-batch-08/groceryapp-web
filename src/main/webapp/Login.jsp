<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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

	<form action="login">
		<label for="pnumber">Mobile Number</label><br> <input type="text"
			name="uname" required autofocus pattern="[6-9]{1}[0-9]{9}"
			placeholder="Enter Mobile number"> <br>
		<br> <label for="pword">Password</label><br> <input
			type="password" name="pword" required min="8"
			placeholder="Enter password"> <br> <br> <input
			type="submit" class="btn btn-success" value="Login"><br>
		<br>
		<%String erroruserid=(String)session.getAttribute("erroruserid");
if(erroruserid!=null){ %>
		<p id="errorcontent"><%=erroruserid %></p>
		<% session.removeAttribute("erroruserid");} %>
	</form>
	<label>New User? </label>
	<input type="button" class="btn btn-primary" value="Sign Up"
		onclick="window.location='signup.jsp'">
</body>
</html>