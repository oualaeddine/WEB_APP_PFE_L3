<%@ page import="model.beans.Visite" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.ClientDAO" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="utils.Util" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.db.daos.EmployeDAO" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 16/03/2018
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <%
        Employe employe = (Employe) new EmployeDAO().getById(1);
        out.print(Util.getReintegrerEmployeEmail(employe));
    %>

</body>
</html>
