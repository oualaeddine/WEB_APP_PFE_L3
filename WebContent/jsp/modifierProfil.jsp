<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="model.beans.humans.Employe" %>
<!DOCTYPE html>
<html lang="fr">
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
    <title>Modifier profil</title>
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
        <div class="card-header">Modifier profil</div>
        <div class="card-body">
            <form method="post" id="modifierProfilForm">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="prenomInput">Prenom</label>
                            <input class="form-control" id="prenomInput" name="prenomInput" type="text" value="<%out.print(employe.getPrenom());%>">
                        </div>
                        <div class="col-md-6">
                            <label for="nomInput">Nom</label>
                            <input class="form-control" id="nomInput" name="nomInput" type="text" value="<%out.print(employe.getNom());%>">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputTel">Telephone</label>
                            <input class="form-control" id="inputTel" name="inputTel" type="text" value="<%out.print(employe.getTel());%>">
                        </div>
                        <div class="col-md-6">
                            <label for="dateNaissance">Date de naissance</label>
                            <input class="form-control" id="dateNaissance" name="dateNaissance" type="date" value="<%out.print(employe.getDateNaissance());%>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12">
                            <label for="adresseInput">Adresse</label>
                            <input name="adresseInput" type="text" class="form-control" id="adresseInput" value="<%out.print(employe.getAdresse());%>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="exampleInputEmail1">Email address</label>
                            <input class="form-control" id="exampleInputEmail1" name="emailInput" type="email" value="<%out.print(employe.getEmail());%>">
                        </div>
                        <div class="col-md-6">
                            <label for="usernameInput">Username</label>
                            <input class="form-control" id="usernameInput" name="usernameInput" type="text" value="<%out.print(employe.getUsername());%>">
                        </div>
                    </div>
                </div>
                <%--<div class="form-group">--%>

                <%--</div>--%>

                <div class="text-center">
                    <button class="btn btn-primary btn-block" type="submit" value="register">Sauvegarder</button>
                </div>
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
</body>
<script>
    $(document).ready(function () {
        var validator = $("#modifierProfilForm").bootstrapValidator({
            fields: {
                nomInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nom"
                        }
                    }
                },
                prenomInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre prénom"
                        }
                    }
                },
                inputTel:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer votre numéro de téléphone"
                        },
                        stringLength:{
                            min:10,
                            max:20,
                            message:"Veuillez entrer votre numéro de téléphone"
                        }
                    }
                },
                dateNaissance:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer votre date de naissance"
                        }
                    }
                },
                adresseInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer votre adresse"
                        }
                    }
                },
                emailInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer votre adresse email"
                        },
                        emailAddress:{
                            message:"Veuillez entrer une adresse valide (eg. john@gmail.com)"
                        }
                    }
                },
                usernameInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer votre nom d'utilisateur"
                        }
                    }
                }
            }
        });
    })
</script>
</html>