<%@page import="com.chainsys.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.chainsys.daoimpl.CustomerDaoImpl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<%
  // Create an ArrayList with test data
  ArrayList list = new ArrayList();
  Map author1 = new HashMap();
  author1.put("name", "A");
  author1.put("id", new Integer(1));
  list.add(author1);
  Map author2 = new HashMap();
  author2.put("name", "B");
  author2.put("id", new Integer(2));
  list.add(author2);
  Map author3 = new HashMap();
  author3.put("name", "C");
  author3.put("id", new Integer(3));
  list.add(author3);
  pageContext.setAttribute("authors", list);
%>

<html>
  <head>
    <title>Search result: Authors</title>
  </head>
  <body bgcolor="white">
    Here are all authors matching your search critera:
    <table>
      <TH>Name</th>
      <TH>Id</th>
      <c:forEach items="${authors}" var="current">
        <tr>
          <td><c:out value="${current.name}" /><td>
          <td><c:out value="${current.id}" /><td>
        </tr>
      </c:forEach>
    </table>
          <div class="row">
        <div class="col-sm-12 "> 
          </div>
          </div>
           <% CustomerDaoImpl obj=new CustomerDaoImpl();
List<Customer> userList= obj.viewallLoginUser();
request.setAttribute("userList", userList);
%>
<div class= kon>
<table >
  <thead>
    <tr>
      <th scope="col">Customer ID</th>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Address</th>
       <th scope="col">mobile</th>
        <th scope="col">UserName</th>
        <th scope="col">Email</th>
    </tr>
  </thead>
 <tbody>

 <c:forEach items="${userList}" var="current">
<tr>
 <td><c:out value="${current.getCustomerid()}" /><td>
 <td><c:out value="${current.getFirstName()}" /><td>
 <td><c:out value="${current.getLastName()}" /><td>
  <td><c:out value="${current.getAddress()}" /><td>
    <td><c:out value="${current.getPhonenumber()}" /><td>
     <td><c:out value="${current.getUsername()}" /><td>
          <td><c:out value="${current.getEmailid()}" /><td>
</tr>
</c:forEach>
</tbody>
</table>
 </div>
 
    
  </body>
</html>