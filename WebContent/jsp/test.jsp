<%@ page import="model.beans.Visite" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%@ page import="java.util.LinkedList" %><%--
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
        LinkedList<Visite> allVisits = new VisitesDao().getPassee();
        for (Visite visite : allVisits){
        out.print("id: " + visite.getId() + " logement: " + visite.getLogement().getTitre() + " agent: " + visite.getAgent().getUsername() + " client: " + visite.getClient().getUsername() + " timestamp: " + visite.getTime() + " Etat: " + visite.getEtatVisite());

    }%>

</body>
</html>
