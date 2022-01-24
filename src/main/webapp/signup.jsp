<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
    <h3>Sign Up</h3>
    <form action="signup">
        <label for="cname">Enter User Name</label> 
		<input type="text"name="cname" autofocus pattern="[1-9a-zA-Z]{3,}"> <br> <br>
		<label for="pnome">Enter Mobile Number</label> 
        <input type="text" name="pnumber"  required  pattern="[6-9]{1}[0-9]{9}"> <br> <br>
        <label for="fname">Enter First Name</label> 
        <input type="text" name="fname" required  pattern="[a-zA-Z]{3,}"> <br> <br>
		<label for="lname">Enter Last Name</label> 
        <input type="text" name="lname" required pattern="[a-zA-Z]{3,}" > <br> <br>
        <label for="password"> Set Password</label> 
        <input type="password" name="password" required min="8" > <br> <br>
        <label for="eid">E-mail </label> 
        <input type="text" name="eid" pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}" required> <br> <br>
        <label for="address">Enter the Address</label> 
        <input type="text" name="address"required maxlength="128"> <br> <br>
        <input type="submit" value="Submit">  <br> <br><br>
    </form> 
    <label>Already User? </label> 
    <input type="button" value="login in" onclick="window.location='Login.jsp'" >
     
</body>
</html>