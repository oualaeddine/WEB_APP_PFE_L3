<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="model.enums.TablePage" %>
<%@ page import="model.beans.humans.Employe" %>
<!DOCTYPE html>
<html lang="en">
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);

    String currentPage = "CHANGER_MOT_DE_PASSE";
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
    <title>Modifier mot de passe</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <link href="../css/bootstrapValidator.min.css" rel="stylesheet">

    <style>
        .nav-link:hover{
            background-color: rgba(21, 21, 21, 0.81);
        }
    </style>
</head>

<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand" href="#"><%out.print(tablesView.getNav().getTitle()+": "+employe.getNom()+" "+employe.getPrenom());%></a>
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

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Principale</li>
    </ol>
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Modifier mot de passe</div>
        <div class="card-body">
            <form method="post" id="changePasswordForm" action="/ChangePassword">

                <div class="form-group" align="center">
                    <label for="id">Veuillez entrer le nouveau mot de passe</label>
                    <input class="form-control" id="id" name="newMdp" type="password" placeholder="Nouveau mot de passe">
                </div>
                <div class="form-group" align="center">
                    <label for="confirm_new_mdp">Veuillez confirmer le mot de passe</label>
                    <input class="form-control" id="confirm_new_mdp" name="confirm_new_mdp" type="password" placeholder="Confirmez nouveau mot de passe">
                </div>
                <button class="btn btn-primary btn-block" type="submit">Changer mot de passe</button>
            </form>

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
</div>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../js/bootstrapValidator.min.js"></script>
<%
    if (request.getParameter("error") != null) {
        int errorId = Integer.parseInt(request.getParameter("error"));
        String errorMessage = "Unidentified error";
        switch (errorId) {
            case MyServlet.ACTION_ERROR:
                errorMessage = "Erreur lors de la reinitialisation du mot de passe";
                break;
            case MyServlet.ACTION_SUCCESS:
                errorMessage = "Mot de passe réinitialisé";
                break;
        }
        out.print("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
%>
</body>
<script>
    $(document).ready(function () {
        var validator = $("#changePasswordForm").bootstrapValidator({
            fields: {
                newMdp: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer le nouveau mot de passe"
                        },
                        stringLength: {
                            min: 6,
                            message: "Le mot de passe doit contenir au moins 6 caractères"
                        }
                    }
                },
                confirm_new_mdp: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez confirmer votre nouveau mot de passe"
                        },
                        identical:{
                            field:"newMdp",
                            message:"Les deux mots de passe ne sont pas identiques"
                        }
                    }
                }
            }
        });
    })
</script>
<style>
    .has-error .help-block {
        color: red;
    }

    small.help-block {
        color: #F44336 !important;
    }
</style>
</html>