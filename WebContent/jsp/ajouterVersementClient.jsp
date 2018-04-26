<%@ page import="model.beans.views.MyView" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Espace Super Utilisateur</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <link rel="stylesheet" href="../programmerVisite/assets/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="../programmerVisite/assets/datatables-select/select.dataTables.min.css">
    <link rel="stylesheet" href="../programmerVisite/assets/datatables-responsive/responsive.dataTables.min.css">
    <style>
        .nav-link:hover {
            background-color: rgba(21, 21, 21, 0.81);
        }
    </style>
</head>

<body class="fixed-nav sticky-footer bg-white" id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%>  sidebar fixed-top"
     id="mainNav">
    <a class="navbar-brand"
       href="#"><%out.print(tablesView.getNav().getTitle() + ": " + employe.getNom() + " " + employe.getPrenom());%></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav scroll-nav  navbar-sidenav" id="exampleAccordion">
            <% for (MyView navElement : tablesView.getNav().getElements()) {
                out.print(navElement.getHtml());
            }
            %>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>

    </div>
</nav>
<div class="content-wrapper">

    <div class="table-responsive">
        <table id="clientsTable" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
                <th>Date de naissance</th>
                <th>Etat</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <input type="text" name="clientId" id="clientId">
    </div>
</div>

<footer class="sticky-footer">
    <div class="container">
        <div class="text-center">
            <small>Copyright © Berrehal-Benghezal-Rehab PFE GL L3 2018</small>
        </div>
    </div>
</footer>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>
<!-- Logout Modal-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Page level plugin JavaScript-->
<script src="../vendor/chart.js/Chart.min.js"></script>
<script src="../vendor/datatables/jquery.dataTables.js"></script>
<script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
<!-- Custom scripts for all pages-->
<script src="../js/sb-admin.min.js"></script>
<!-- Custom scripts for this page-->
<script src="../js/sb-admin-datatables.min.js"></script>
<script src="../js/sb-admin-charts.js"></script>
<script src="../programmerVisite/assets/js/main.js"></script>
<script src="../programmerVisite/assets/datatables.net/js/jquery.dataTables.js"></script>
<script src="../programmerVisite/assets/datatables.net-bs/js/dataTables.bootstrap.js"></script>
<script src="../programmerVisite/assets/datatables-select/dataTables.select.min.js"></script>
<script src="../programmerVisite/assets/datatables-responsive/dataTables.responsive.min.js.min.js"></script>
</div>
</body>

<script>
    var table = $('#clientsTable').DataTable({
        'paging': true,
        'lengthChange': false,
        'searching': true,
        'info': true,
        'autoWidth': false,
        select: {
            style: 'single'
        },
        "processing": true,
        "serverSide": true,
        ajax: {
            url: '/api/clientApi?action=getAllClients',
            dataSrc: ''
        },
        columns: [
            {"data": "id"},
            {"data": "nom"},
            {"data": "prenom"},
            {"data": "telephone"},
            {"data": "dateDeNaissance"},
            {"data": "isBanned"}
        ]
    });

    table.on('select', function (e, dt, type, indexes) {
        var rowData = table.rows(indexes).data().toArray();
        $('#clientId').val(rowData[0]['id']);
        document.getElementById("clientIdDetails").innerHTML = rowData[0]['id'];
    });
</script>


</html>