<%@ page import="model.beans.Visite" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.humans.ResponsableVente" %>
<%@ page import="model.db.daos.ResponsableVentesDAO" %><%--
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
        LinkedList<ResponsableVente> all = new ResponsableVentesDAO().getAll();
        for (ResponsableVente responsableVente : all){
        out.print("id: " + responsableVente.getId() + " nom: " + responsableVente.getNom()+ " addedBy: "+responsableVente.getCreator().getNom());

    }%>

</body>
</html>
