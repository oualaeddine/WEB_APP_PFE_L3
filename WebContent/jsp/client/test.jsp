<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Client" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 17/04/2018
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Client client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);  %>
<%out.print(client.getUsername()+" "+client.getEmail());%>



</body>
</html>
