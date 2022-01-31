<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
<h1>Logout successfully</h1>
<%session.invalidate(); %>
<script>
         setTimeout(function(){
            window.location.href = 'login.jsp';
         }, 2000);
      </script>
</body>
</html>