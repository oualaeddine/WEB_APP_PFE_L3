<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Versement" %>
<%@ page import="model.db.daos.VersementDAO" %>
<%@ page import="model.db.daos.VentesDAO" %>
<%@ page import="model.beans.Vente" %><%--
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

<table id="table">
    <thead>
    <tr>
        <th>Id vente</th>
        <th>Logement</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <%
        LinkedList<Vente> ventes = new VentesDAO().getEnCours();

        for (Vente vente : ventes) {
            out.print("" +
                    "<tr onclick=\"getIdVenteForVersement(" + vente.getId() + ")\" data-toggle=\"modal\" data-target=\"#versementsByVenteModal\">" +
                    "       <td>" + vente.getId() + "</td>" +
                    "       <td>" + vente.getLogement().getTitre() + "</td>" +
                    "       <td>" + vente.getDate() + "</td>" +
                    "</tr>");
        }
    %>
    </tbody>
</table>


<div id="versementsByVenteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Liste des versements</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <table id="tableModal">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Montant</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function getIdVenteForVersement(idTaaLaVente) {
        $.ajax({
            url: "/api/versementApi?action=getByVente&venteId=" + idTaaLaVente,
            success: function (result) {
                var table = $("#tableModal tbody");
                $.each(result, function (idx, elem) {
                    table.append("<tr><td>" + elem.username + "</td><td>" + elem.name + "</td>   <td>" + elem.lastname + "</td></tr>");
                });
            }
        })
    }

</script>
</html>
