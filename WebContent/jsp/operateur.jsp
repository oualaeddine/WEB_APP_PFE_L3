<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.db.daos.AppelsDAO" %>
<%@ page import="control.statistics.globales.VisitesStats" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page import="control.statistics.globales.ClientsStats" %>
<!DOCTYPE html>
<html lang="en">
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = "Operateur";
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
    Employe operateur = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);

%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Espace operateur</title>
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

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand" href="#"><%out.print(tablesView.getNav().getTitle()+": "+operateur.getNom()+" "+operateur.getPrenom());%></a>
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
    <div class="container-fluid">
        <!-- Icon Cards-->
        <div class="row">
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-primary o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-comments"></i>
                        </div>
                        <div class="mr-5"><%out.print(new AppelsDAO().getNonConfirmes().size());%> Nouveaux appels!
                        </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="/DashboardServlet?what=confirmerAppel">
                        <span class="float-left">Afficher les appels</span>
                        <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-warning o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-list"></i>
                        </div>
                        <div class="mr-5"><%out.print(new VisitesStats().nbrVisitesPrevuesForMonth(Month.of(Calendar.getInstance().get(Calendar.MONTH))));%>
                            Visites reportées!
                        </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1"
                       href="/DashboardServlet?what=visitesProgrammees">
                        <span class="float-left">Afficher les visites prévues</span>
                        <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-success o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-users"></i>
                        </div>
                        <div class="mr-5"><%out.print(new ClientsStats().newClientsThisMonthNbr());%> Nouveaux clients
                        </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les clients</span>
                        <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-danger o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fa fa-fw fa-ban"></i>
                        </div>
                        <div class="mr-5"><%out.print(new VisitesStats().nbrVisitesAnnuleesForMonth(Month.of(Calendar.getInstance().get(Calendar.MONTH))));%>
                            Visites annulées!
                        </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les visites annulées</span>
                        <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
                    </a>
                </div>
            </div>
        </div>
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Principale</li>
        </ol>
        <div>
            <iframe src="./statistics/operateur_stats.jsp" width="100%" frameborder="0" scrolling="no"
                    onload="resizeIframe(this)"></iframe>
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
                    <h5 class="modal-title" id="exampleModalLabel">Prêt à partir ?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Selectionnez "Déconnexion" ci dessous si vous êtes prêts à fermer votre
                    session.
                </div>
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
<%
    if (request.getParameter("error") != null) {
        int errorId = Integer.parseInt(request.getParameter("error"));
        String errorMessage = "Unidentified error";
        switch (errorId) {
            case MyServlet.ACTION_ERROR:
                errorMessage = "Action non effectuee, Veuillez reessayer";
                break;
            case MyServlet.ACTION_SUCCESS:
                errorMessage = "Action bien effectuee !";
                break;
            case MyServlet.ACCESS_DENIED:
                errorMessage = "Accès refusé";
                break;
        }
        out.print("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
%>
</body>
<script>
    function resizeIframe(obj) {
        obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
    }
</script>
</html>