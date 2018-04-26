<%@ page import="model.beans.views.MyView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.humans.Employe" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = "Responsable ventes";
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
    Employe agent = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);

%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Espace Responsable Ventes</title>
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
        .nav-link:hover {
            background-color: rgba(21, 21, 21, 0.81);
        }
    </style>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand"
       href="#"><%out.print(tablesView.getNav().getTitle() + ": " + agent.getNom() + " " + agent.getPrenom());%></a>
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
                        <div class="mr-5">26 Nouveaux Messages!</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les messages</span>
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
                        <div class="mr-5">11 Visites reportées!</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les details</span>
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
                        <div class="mr-5">13 Nouvelles Visites!</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les Details</span>
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
                        <div class="mr-5">7 Visites annulées!</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                        <span class="float-left">Afficher les Details</span>
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
        <!-- Area Chart Example-->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-chart-area"></i> Changement nombre de clients
            </div>
            <div class="card-body">
                <canvas id="myAreaChart" width="100%" height="30"></canvas>
            </div>
            <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
        </div>
        <div class="row">
            <div class="col-lg-8">
                <!-- Example Bar Chart Card-->
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-chart-bar"></i> Nombre de clients par mois
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-8 my-auto">
                                <canvas id="myBarChart" width="100" height="69"></canvas>
                            </div>
                            <div class="col-sm-4 text-center my-auto">
                                <div class="h4 mb-0 text-primary">$34,693</div>
                                <div class="small text-muted">YTD Revenue</div>
                                <hr>
                                <div class="h4 mb-0 text-warning">$18,474</div>
                                <div class="small text-muted">YTD Expenses</div>
                                <hr>
                                <div class="h4 mb-0 text-success">$16,219</div>
                                <div class="small text-muted">YTD Margin</div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
                </div>
            </div>
            <div class="col-lg-4">
                <!-- Example Pie Chart Card-->
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-chart-pie"></i> Nombre de clients par region
                    </div>
                    <div class="card-body">
                        <canvas id="myPieChart" width="100%" height="100"></canvas>
                    </div>
                    <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
                </div>
                <!-- Example Notifications Card-->
                <!-- <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-bell"></i> Activité</div>
                    <div class="list-group list-group-flush small">
                        <a class="list-group-item list-group-item-action" href="#">
                            <div class="media">
                                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                                <div class="media-body">
                                    <strong>David Miller</strong>posted a new article to
                                    <strong>David Miller Website</strong>.
                                    <div class="text-muted smaller">Today at 5:43 PM - 5m ago</div>
                                </div>
                            </div>
                        </a>
                        <a class="list-group-item list-group-item-action" href="#">
                            <div class="media">
                                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                                <div class="media-body">
                                    <strong>Samantha King</strong>sent you a new message!
                                    <div class="text-muted smaller">Today at 4:37 PM - 1hr ago</div>
                                </div>
                            </div>
                        </a>
                        <a class="list-group-item list-group-item-action" href="#">
                            <div class="media">
                                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                                <div class="media-body">
                                    <strong>Jeffery Wellings</strong>added a new photo to the album
                                    <strong>Beach</strong>.
                                    <div class="text-muted smaller">Today at 4:31 PM - 1hr ago</div>
                                </div>
                            </div>
                        </a>
                        <a class="list-group-item list-group-item-action" href="#">
                            <div class="media">
                                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                                <div class="media-body">
                                    <i class="fa fa-code-branch"></i>
                                    <strong>Monica Dennis</strong>forked the
                                    <strong>startbootstrap-sb-admin</strong>repository on
                                    <strong>GitHub</strong>.
                                    <div class="text-muted smaller">Today at 3:54 PM - 2hrs ago</div>
                                </div>
                            </div>
                        </a>
                        <a class="list-group-item list-group-item-action" href="#">View all activity...</a>
                    </div>
                    <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
                </div> -->
            </div>
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
</div>
</body>

</html>