<%@ page import="model.beans.humans.Client" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="utils.Util" %>
<%@ page import="model.beans.views.table.DataTableRow" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.db.ContactInfosDAO" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 27/04/2018
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>

<%Client client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);%>
<%
    String title = Util.getPageTitleFromPageType(Util.getPageFromString(request.getParameter("page")));
    String currentPage = request.getParameter("page");
    tablesView.setLoggedInUserId(client.getId());
    tablesView.setLoggedInUserType(UserType.CLIENT);
    tablesView.setCurrentPage(currentPage);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="GARO is a real-estate template">
    <meta name="author" content="Kimarotec">
    <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

    <!-- Place favicon.ico  the root directory -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../../assets_client/css/normalize.css">
    <link rel="stylesheet" href="../../assets_client/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets_client/css/fontello.css">
    <link href="../../assets_client/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../../assets_client/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
    <link href="../../assets_client/css/animate.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../../assets_client/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../vendor/bootstrap_client/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets_client/css/icheck.min_all.css">
    <link rel="stylesheet" href="../../assets_client/css/price-range.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.carousel.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.theme.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.transitions.css">
    <link rel="stylesheet" href="../../assets_client/css/style.css">
    <link rel="stylesheet" href="../../assets_client/css/responsive.css">

    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="../vendor/datatables/buttons.dataTables.min.css" rel="stylesheet">
    <link href="../vendor/datatables/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../../programmerVisite/assets/datatables-select/select.dataTables.min.css">
    <link href="../../programmerVisite/assets/datatables-responsive/responsive.dataTables.min.css">

    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | <%out.print(title);%></title>
</head>
<body>
<div id="include_html"></div>

<div class="content-wrapper">
    <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>
            <tr>
                <%out.print(tablesView.getDataTable().getTableHeader());%>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <%out.print(tablesView.getDataTable().getTableHeader());%>
            </tr>
            </tfoot>
            <tbody>

            <% LinkedList<DataTableRow> list = tablesView.getDataTable().getTableData();
                for (DataTableRow tableRow : list) {
                    out.print(tableRow.getHtml());
                }%>
            </tbody>
        </table>
    </div>
    <%
        if (currentPage.equals("CLIENT_MY_VISITS")) {
            out.print("<div class=\"text-center\">\n" +
                    "        <a href=\"/DashboardServlet?what=modifierVisite\"> <button class=\"btn btn-default\" >Modifier une visite</button></a>\n" +
                    "    </div>");
        }
    %>

</div>
<div id="footer_include"></div>

<%--Annuler/reporter visite--%>
<div id="modifierVisiteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Modifier visite</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form method="get" action="/modifierVisiteServlet" id="modifierVisiteForm">

                    <input id="visiteModifiee" name="nouvelleDate" type="hidden">
                    <div class="form-group">
                        <label for="what">Veuillez séléctionner le type de la modification</label>
                        <select name="what" id="what" class="form-control">
                            <option value="0" disabled="" selected="">- action -</option>
                            <option value="annuler"> cancel</option>
                            <option value="reporter"> reporter</option>
                        </select>
                    </div>
                    <input type="hidden" name="clientId" id="clientId">
                    <input type="hidden" name="visiteId" id="visiteId">
                    <input type="hidden" name="regionId" id="regionId">
                    <input type="hidden" name="newDate" id="newDate">
                    <input type="hidden" name="logementId" id="logementId">

                    <%--<button class="btn btn-info btn-lg" type="button" onclick="modifierVisite()">Valider</button>--%>
                </form>

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" form="modifierVisiteForm">Valider</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<%--Details vente modal--%>
<div id="venteDetails" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Détails de la vente</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <table class="display nowrap" style="width:100%">

                    <tr>
                        <td>ID:</td>
                        <td><span id="venteIdDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Nom et prénom du client:</td>
                        <td><span id="venteClientDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Logement:</td>
                        <td><span id="venteLogementDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Date:</td>
                        <td><span id="venteDateDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Montant restant à payer:</td>
                        <td><span id="venteRestantDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Etat:</td>
                        <td><span id="venteEtatDetails"></span></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<%--Details visite modal--%>
<div id="visiteDetails" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Détails de la visite</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <table class="display nowrap" style="width:100%">

                    <tr>
                        <td>ID de la visite:</td>
                        <td><span id="visiteIdDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Logement:</td>
                        <td><span id="visiteLogementDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Agent:</td>
                        <td><span id="visiteAgentDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Client:</td>
                        <td><span id="visiteClientDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Date et heure:</td>
                        <td><span id="visiteTimestampDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Etat:</td>
                        <td><span id="visiteEtatDetails"></span></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<%--Details logements modal--%>
<div id="logementDetails" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Détails du logement</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <table class="display nowrap" style="width:100%">

                    <tr>
                        <td>ID:</td>
                        <td><span id="logementIdDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Titre:</td>
                        <td><span id="logementTitreDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><span id="logementDescriptionDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Superficie:</td>
                        <td><span id="logementSuperficieDetails"></span> m2</td>
                    </tr>
                    <tr>
                        <td>Nombre de pièces:</td>
                        <td><span id="logementNbrPieces"></span></td>
                    </tr>
                    <tr>
                        <td>Nombre de salles de bain:</td>
                        <td><span id="logementNbrSdb"></span></td>
                    </tr>
                    <tr>
                        <td>Avec jardin:</td>
                        <td><span id="logementJardinDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Avec garage:</td>
                        <td><span id="logementGarageDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Avec sous-sol:</td>
                        <td><span id="logementSousSolDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Meublé:</td>
                        <td><span id="logementMeubleDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Adresse:</td>
                        <td><span id="logementAdresseDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Localite:</td>
                        <td><span id="logementLocaliteDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Prix:</td>
                        <td><span id="logementPrixDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Etage:</td>
                        <td><span id="logementEtageDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Gelé:</td>
                        <td><span id="logementEtatDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Latitude:</td>
                        <td><span id="logementLatitudeDetails"></span></td>
                    </tr>
                    <tr>
                        <td>Longitude:</td>
                        <td><span id="logementLongitudeDetails"></span></td>
                    </tr>
                </table>
                <form method="post" action="/SuppressionServlet" id="supprimerLogementForm">
                    <input type="hidden" name="supprimer" value="logement">
                    <input type="hidden" id="deletedLogementId" name="deletedLogementId">
                </form>
            </div>
            <div class="modal-footer">
                <%
                    if (request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE) == UserType.ADMIN || request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE) == UserType.SU) {
                        out.print("<a href=\"\"><button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Modifier</button></a>");
                        out.print("<button type=\"submit\" form=\"supprimerLogementForm\" class=\"btn btn-default\">Supprimer</button>");
                    } else {
                        out.print("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Fermer</button>");
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });
</script>
<script src="../../assets_client/js/modernizr-2.6.2.min.js"></script>

<script src="../../assets_client/js/jquery-1.10.2.min.js"></script>
<script src="../../vendor/bootstrap_client/js/bootstrap.min.js"></script>
<script src="../../assets_client/js/bootstrap-select.min.js"></script>
<script src="../../assets_client/js/bootstrap-hover-dropdown.js"></script>

<script src="../../assets_client/js/easypiechart.min.js"></script>
<script src="../../assets_client/js/jquery.easypiechart.min.js"></script>

<script src="../../assets_client/js/owl.carousel.min.js"></script>

<script src="../../assets_client/js/wow.js"></script>

<script src="../../assets_client/js/icheck.min.js"></script>
<script src="../../assets_client/js/price-range.js"></script>

<script src="../../assets_client/js/main.js"></script>
<script src="./vendor/datatables/jquery.dataTables.js"></script>
<script src="./vendor/datatables/dataTables.bootstrap4.js"></script>

<script src="../../vendor/datatables/dataTables.buttons.min.js"></script>
<script src="../../vendor/datatables/buttons.flash.min.js"></script>
<script src="../../vendor/datatables/jszip.min.js"></script>
<script src="../../vendor/datatables/pdfmake.min.js"></script>
<script src="../../vendor/datatables/vfs_fonts.js"></script>
<script src="../../vendor/datatables/buttons.html5.min.js"></script>
<script src="../../vendor/datatables/buttons.print.min.js"></script>
<script src="../../js/bootstrapValidator.min.js"></script>
<script src="../../programmerVisite/assets/datatables-responsive/dataTables.responsive.min.js"></script>
<script src="../../programmerVisite/assets/datatables-select/dataTables.select.min.js"></script>

<script>
    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });

    var table = $('#dataTable').DataTable({
        processing: true,
        select: true,
        dom: 'Bfrtip',
        buttons: [
            'copy',
            'csv',
            'excel',
            {
                extend: 'pdfHtml5',
                messageTop: 'Ce fichier est créé pour : <%out.print(client.getFullName());%>'
            },
            {
                extend: 'print',
                messageTop: 'Ce fichier est créé pour : <%out.print(client.getFullName());%>'
            }
        ]

    });

    function afficherDetailsVente(rowData) {
        $.ajax({
            type: 'GET',
            url: '/api/venteApi?action=getById&id=' + rowData[0][0],
            success: function (result) {
                var vente = JSON.parse(result);
                document.getElementById("venteIdDetails").innerHTML = vente.id;
                document.getElementById("venteClientDetails").innerHTML = vente.client.nom + ' ' + vente.client.prenom;
                document.getElementById("venteLogementDetails").innerHTML = 'ID:' + vente.logement.id + ' | Titre: ' + vente.logement.titre;
                document.getElementById("venteDateDetails").innerHTML = vente.date;
                document.getElementById("venteRestantDetails").innerHTML = rowData[0][4] + ' DA';
                document.getElementById("venteEtatDetails").innerHTML = vente.etatVente;
            }
        });
        $("#venteDetails").modal({
            show: true
        });
    } // ok

    function afficherDetailsVisite(rowData) {
        $.ajax({
            type: 'GET',
            url: '/api/visiteApi?action=getById&id=' + rowData[0][0],
            success: function (result) {
                var visite = JSON.parse(result);
                var horraire;
                switch (visite.horraire) {
                    case 1:
                        horraire = '08:00 - 10:00';
                        break;
                    case 2:
                        horraire = '10:00 - 12:00';
                        break;
                    case 3:
                        horraire = '12:00 - 14:00';
                        break;
                    case 4:
                        horraire = '14:00 - 16:00';
                        break;
                }
                document.getElementById("visiteIdDetails").innerHTML = visite.id;
                document.getElementById("visiteLogementDetails").innerHTML = 'ID: ' + visite.logement.id + ' | Titre: ' + visite.logement.titre;
                document.getElementById("visiteAgentDetails").innerHTML = visite.agent.nom + ' ' + visite.agent.prenom;
                document.getElementById("visiteClientDetails").innerHTML = visite.client.nom + ' ' + visite.client.prenom;
                document.getElementById("visiteTimestampDetails").innerHTML = visite.timestamp + ' | ' + horraire;
                document.getElementById("visiteEtatDetails").innerHTML = visite.etatVisite;
            }
        });
        $("#visiteDetails").modal({
            show: true
        });
    } // ok

    function afficherDetailsLogement(rowData) {
        $.ajax({
            type: 'GET',
            url: '/api/logementApi?action=getById&id=' + rowData[0][0],
            success: function (result) {
                var logement = JSON.parse(result);
                document.getElementById("logementIdDetails").innerHTML = logement.id;
                document.getElementById("logementTitreDetails").innerHTML = logement.titre;
                document.getElementById("logementDescriptionDetails").innerHTML = logement.description;
                document.getElementById("logementSuperficieDetails").innerHTML = logement.superficie;
                document.getElementById("logementNbrPieces").innerHTML = logement.nbrPieces;
                document.getElementById("logementNbrSdb").innerHTML = logement.nbrSdb;
                document.getElementById("logementJardinDetails").innerHTML = logement.avecJardin;
                document.getElementById("logementGarageDetails").innerHTML = logement.avecGarage;
                document.getElementById("logementSousSolDetails").innerHTML = logement.avecSousSol;
                document.getElementById("logementMeubleDetails").innerHTML = logement.meubles;
                document.getElementById("logementAdresseDetails").innerHTML = logement.adresse;
                document.getElementById("logementLocaliteDetails").innerHTML = logement.localite.nom;
                document.getElementById("logementPrixDetails").innerHTML = logement.prix + ' DA';
                document.getElementById("logementEtageDetails").innerHTML = logement.etage;
                document.getElementById("logementEtatDetails").innerHTML = logement.isGele;
                document.getElementById("logementLatitudeDetails").innerHTML = logement.location.latitude;
                document.getElementById("logementLongitudeDetails").innerHTML = logement.location.longitude;
                document.getElementById("deletedLogementId").value = logement.id;
            }
        });
        $("#logementDetails").modal({
            show: true
        });
    } // ok


    table.on('select', function (e, dt, type, indexes) {
        var rowData = table.rows(indexes).data().toArray();

        var page = '<%out.print(currentPage);%>';
        switch (page) {
            case 'CLIENT_MES_VENTES_EN_COURS':
                afficherDetailsVente(rowData);
                break;
            case 'CLIENT_MY_VISITS':
                afficherDetailsVisite(rowData);
                break;
            case 'CLIENT_MES_LOGEMENT_VISITES':
                afficherDetailsLogement(rowData);
                break;
        }
    });

    <%--var table = $('#dataTable').DataTable({--%>
    <%--dom: 'Bfrtip',--%>
    <%--buttons: [--%>
    <%--'copy',--%>
    <%--'csv',--%>
    <%--'excel',--%>
    <%--{--%>
    <%--extend: 'pdfHtml5',--%>
    <%--messageTop: 'Ce fichier est créé pour : <%out.print(client.getFullName());%>'--%>
    <%--},--%>
    <%--{--%>
    <%--extend: 'print',--%>
    <%--messageTop: 'Ce fichier est créé pour : <%out.print(client.getFullName());%>'--%>
    <%--}--%>
    <%--]--%>
    <%--});--%>


    function getVisiteModifieeId(idtaalavisite, idtaalogement, idtaalaregion, idtaalclient) {

        document.getElementById("visiteModifiee").value = idtaalavisite;
        document.getElementById("visiteId").value = idtaalavisite;
        document.getElementById("clientId").value = idtaalclient;
        document.getElementById("logementId").value = idtaalogement;
        document.getElementById("regionId").value = idtaalaregion;
    }
</script>
</html>
