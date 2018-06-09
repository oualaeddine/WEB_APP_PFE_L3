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

    $("#dataTable").DataTable({
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
            },
            {
                text: 'Annuler visite'
            }
        ]
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
