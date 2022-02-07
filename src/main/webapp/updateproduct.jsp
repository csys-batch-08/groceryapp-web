<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
     <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="allusers">

<form action="Addproduct" method="post">
<div class="mb-3">
  <label for="formGroupExampleInput" class="form-label">Enter the product name</label>
  <input type="text" class="form-control" name="pname" value="<c:out value="${prs}" />" id="formGroupExampleInput" placeholder="Product Name" required >
</div>
<div class="mb-3">
  <label for="formGroupExampleInput2" class="form-label">Enter the Price</label>
  <input type="text" class="form-control" name="price" id="formGroupExampleInput2" placeholder="Product Price" pattern="[1-9]{1}[0-9]+"  required >
</div>
<div class="mb-3">
  <label for="formGroupExampleInput3" class="form-label">Enter the Price</label>
  <input type="file" class="form-control" required name="productimage" id="formGroupExampleInput3" placeholder="Product image"   required >
</div>
<input type="submit" value="submit">
</form>
</div>

</body>
</html>