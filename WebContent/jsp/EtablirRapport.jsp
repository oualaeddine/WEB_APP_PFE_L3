<%@ page import="model.beans.Visite" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.beans.views.MyView" %>
Created by IntelliJ IDEA.
  User: hp
  Date: 23/02/2018
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = request.getParameter("page");
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Espace Agent</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <style>
        .nav-link:hover{
            background-color: rgba(21, 21, 21, 0.81);
        }
    </style>
</head>
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
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
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->

<div class="content-wrapper">
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Principale</li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> Liste des clients</div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>

                        <tr>
                            <th>id</th>
                            <th>Logement</th>
                            <th>Client</th>
                            <th>Date</th>
                            <th>Etat</th>
                            <th>Rediger rapport</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>id</th>
                            <th>Logement</th>
                            <th>Client</th>
                            <th>Date</th>
                            <th>Etat</th>
                            <th>Rediger rapport</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <%  Agent agent = (Agent)request.getSession().getAttribute("user");
                            LinkedList<Visite> list = new VisitesDao().getVisitesByAgent(agent); %>
                        <%
                            for (Visite visite: list) {
                                out.print("<tr>\n" +
                                        "<td>"+visite.getId()+"</td>\n" +
                                        "<td>"+visite.getLogement().getTitre()+
                                        "<td>"+visite.getClient().getNom()+" "+visite.getClient().getPrenom()+"</td>\n" +
                                        "<td>"+visite.getTime()+"</td>\n" +
                                        "<td>"+visite.getEtatVisite()+"</td>\n" +
                                        "<td><a href=\"/NewRapport?visite="+visite.getId()+"\">Rapport</a></td>                        </tr>");
                            }

                        %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
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
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
</div>
</body>

</html>
