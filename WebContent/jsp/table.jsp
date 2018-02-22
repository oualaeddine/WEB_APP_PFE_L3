<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.beans.views.NavElement" %>
<%@ page import="model.beans.views.DataTableRow" %>
<%@ page import="static utils.Consts.FOOTER_COPYRIGHT" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Person" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 2/17/2018
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private TablesView tablesView = new TablesView(); %>
<%        // TODO: 2/18/2018  test this jsp


    UserType userType =(UserType) request.getSession().getAttribute("userType");
//    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    Person person =(Person) request.getSession().getAttribute("loggedIn");
    String currentPage =  request.getParameter("page");
    tablesView.setLoggedInUserId(person.getId());
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%out.print(tablesView.getPageTitle());%></title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->

<nav class="navbar navbar-expand-lg navbar-dark <%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top"
     id="mainNav">
    <a class="navbar-brand" href="#"><%out.print(tablesView.getNav().getTitle());%></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav scroll-nav  navbar-sidenav" id="exampleAccordion">
            <% for (NavElement navElement : tablesView.getNav().getElements()) {
                out.print(navElement.toString());
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
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#"><%out.print(tablesView.getPageTitle());%></a>
            </li>
            <li class="breadcrumb-item active"><%out.print(tablesView.getBreadcrumbTitle());%></li>
        </ol>
        <!-- Example DataTables Card-->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> <%out.print(tablesView.getDataTable().getDataTableTitle());%>
            </div>
            <div class="card-body">
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
                        <% for (DataTableRow tableRow : tablesView.getDataTable().getTableData()) {
                            out.print(tableRow.getHtml());
                        }%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">Mis à jour <strong>Maintenant</strong></div>
        </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
        <div class="container">
            <div class="text-center">
                <%out.print(FOOTER_COPYRIGHT); %>
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
                    <a class="btn btn-primary" href="login.html">Logout</a>
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
    <script src="../vendor/datatables/jquery.dataTables.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="../js/sb-admin-datatables.min.js"></script>
</div>
</body>

</html>
