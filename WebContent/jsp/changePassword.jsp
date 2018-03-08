<%@ page import="model.enums.UserType" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Espace Agent - Modifier mot de passe</title>
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

<body class="bg-light">
<%
    switch ((UserType) request.getSession().getAttribute("type")) {
        case AGENT:
            out.print("<nav class=\"navbar navbar-expand-lg navbar-dark navbar-My fixed-top\" id=\"mainNav\">\n" +
                    "    <a class=\"navbar-brand\" href=\"#\">Espace Agent</a>\n" +
                    "    <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                    "        <span class=\"navbar-toggler-icon\"></span>\n" +
                    "    </button>\n" +
                    "    <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
                    "        <ul class=\"navbar-nav scroll-nav navbar-sidenav\" id=\"exampleAccordion\">\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Dashboard\">\n" +
                    "                <a class=\"nav-link\" href=\"/AgentServlet\">\n" +
                    "                    <i class=\"fa fa-fw fa-home\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Principale</span>\n" +
                    "                </a>\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Charts\">\n" +
                    "                <a class=\"nav-link\" href=\"../jsp/EtablirRapport.jsp\">\n" +
                    "                    <i class=\"fa fa-fw fa-edit\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Etablir un rapport</span>\n" +
                    "                </a>\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Tables\">\n" +
                    "                <a class=\"nav-link\">\n" +
                    "                    <i class=\"fa fa-fw fa-envelope\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Messages</span>\n" +
                    "                </a>\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Components\">\n" +
                    "                <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapseComponents\" data-parent=\"#exampleAccordion\">\n" +
                    "                    <i class=\"fa fa-fw fa-eye\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Visites</span>\n" +
                    "                </a>\n" +
                    "                <ul class=\"sidenav-second-level collapse\" id=\"collapseComponents\">\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Visites?what=programmees\"><i class=\"fa fa-fw fa-calendar\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Visites Programmées</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Visites?what=passees\"><i class=\"fa fa-fw fa-calendar-check\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Visites Passées</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Visites?what=annulees\"><i class=\"fa fa-fw fa-calendar-times\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Visites annulées</span></a>\n" +
                    "                    </li>\n" +
                    "                </ul>\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Example Pages\">\n" +
                    "                <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapseClients\" data-parent=\"#exampleAccordion\">\n" +
                    "                    <i class=\"fa fa-fw fa-users\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Clients</span>\n" +
                    "                </a>\n" +
                    "                <ul class=\"sidenav-second-level collapse\" id=\"collapseClients\">\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Clients?what=all\"><i class=\"fa fa-fw fa-bars\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Liste des clients</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Clients?what=banned\"><i class=\"fa fa-fw fa-gratipay\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Clients bannis</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Clients?what=ban\"><i class=\"fa fa-fw fa-ban\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Signaler un client</span></a>\n" +
                    "                    </li>\n" +
                    "                </ul>\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"buildings\">\n" +
                    "                <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapsebuildings\" data-parent=\"#exampleAccordion\">\n" +
                    "                    <i class=\"fa fa-fw fa-building\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Logements</span>\n" +
                    "                </a>\n" +
                    "                <ul class=\"sidenav-second-level collapse\" id=\"collapsebuildings\">\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Logement?what=all\"><i class=\"fa fa-fw fa-list\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Liste des Logements</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Logement?what=vendus\"><i class=\"fa fa-fw fa-check\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Logements vendus</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/Logement?what=geles\"><i class=\"fa fa-fw fa-object-group\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Logements gelés</span></a>\n" +
                    "                    </li>\n" +
                    "                </ul>\n" +
                    "\n" +
                    "            </li>\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Menu Levels\">\n" +
                    "                <a class=\"nav-link nav-link-collapse\" data-toggle=\"collapse\" href=\"#collapseprofile\" data-parent=\"#exampleAccordion\">\n" +
                    "                    <i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Mon profil</span>\n" +
                    "                </a>\n" +
                    "                <ul class=\"sidenav-second-level collapse\" id=\"collapseprofile\">\n" +
                    "                    <li>\n" +
                    "                        <a href=\"/ChangePassword\"><i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Changer mot de passe</span>\n" +
                    "                        </a>\n" +
                    "                    </li>\n" +
                    "                    <li>\n" +
                    "                        <a href=\"\"><i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                            <span class=\"nav-link-text\">Modifier informations</span>\n" +
                    "                        </a>\n" +
                    "                    </li>\n" +
                    "\n" +
                    "                </ul>\n" +
                    "            </li>\n" +
                    "\n" +
                    "            <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Link\">\n" +
                    "                <a class=\"nav-link\" href=\"/logout\">\n" +
                    "                    <i class=\"fa fa-fw fa-sign-out\"></i>\n" +
                    "                    <span class=\"nav-link-text\">Deconnexion</span>\n" +
                    "                </a>\n" +
                    "            </li>\n" +
                    "        </ul>\n" +
                    "        <ul class=\"navbar-nav sidenav-toggler\">\n" +
                    "            <li class=\"nav-item\">\n" +
                    "                <a class=\"nav-link text-center\" id=\"sidenavToggler\">\n" +
                    "                    <i class=\"fa fa-fw fa-angle-left\"></i>\n" +
                    "                </a>\n" +
                    "            </li>\n" +
                    "        </ul>\n" +
                    "\n" +
                    "    </div>\n" +
                    "</nav>");
            break;
        case OPERATEUR:
            out.print("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary sidebar fixed-top\" id=\"mainNav\">\n" +
                    "        <a class=\"navbar-brand\" href=\"#\">Espace Operateur</a>\n" +
                    "        <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                    "      <span class=\"navbar-toggler-icon\"></span>\n" +
                    "    </button>\n" +
                    "        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
                    "            <ul class=\"navbar-nav scroll-nav  navbar-sidenav\" id=\"exampleAccordion\">\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Dashboard\">\n" +
                    "                    <a class=\"nav-link\" href=\"#\">\n" +
                    "                        <i class=\"fa fa-fw fa-home\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Principale</span>\n" +
                    "                    </a>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Charts\">\n" +
                    "                    <a class=\"nav-link\" href=\"/ProgrammerVisite\">\n" +
                    "                        <i class=\"fa fa-fw fa-calendar-plus\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Programmer une visite</span>\n" +
                    "                    </a>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Tables\">\n" +
                    "                    <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#messageComp\" data-parent=\"#exampleAccordion\">\n" +
                    "                        <i class=\"fa fa-fw fa-envelope\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Messages</span>\n" +
                    "                    </a>\n" +
                    "                    <ul class=\"sidenav-second-level collapse\" id=\"messageComp\">\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Messages clients</a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Messages Administration</a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Archive</a>\n" +
                    "                        </li>\n" +
                    "                    </ul>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Components\">\n" +
                    "                    <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapseComponents\" data-parent=\"#exampleAccordion\">\n" +
                    "                        <i class=\"fa fa-fw fa-eye\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Visites</span>\n" +
                    "                    </a>\n" +
                    "                    <ul class=\"sidenav-second-level collapse\" id=\"collapseComponents\">\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Visites Programmées</a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Visites Passées</a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\">Cards</a>\n" +
                    "                        </li>\n" +
                    "                    </ul>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Example Pages\">\n" +
                    "                    <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapseClients\" data-parent=\"#exampleAccordion\">\n" +
                    "                        <i class=\"fa fa-fw fa-users\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Clients</span>\n" +
                    "                    </a>\n" +
                    "                    <ul class=\"sidenav-second-level collapse\" id=\"collapseClients\">\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-bars\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Liste des clients</span></a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-gratipay\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Mes Clients</span></a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-ban\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Signaler un client</span></a>\n" +
                    "                        </li>\n" +
                    "                    </ul>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"buildings\">\n" +
                    "                    <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#collapsebuildings\" data-parent=\"#exampleAccordion\">\n" +
                    "                        <i class=\"fa fa-fw fa-building\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Logements</span>\n" +
                    "                    </a>\n" +
                    "                    <ul class=\"sidenav-second-level collapse\" id=\"collapsebuildings\">\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-list\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Liste des Logements</span></a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-check\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Logements vendus</span></a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"#\"><i class=\"fa fa-fw fa-object-group\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Mes Logements</span></a>\n" +
                    "                        </li>\n" +
                    "                    </ul>\n" +
                    "\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Menu Levels\">\n" +
                    "                    <a class=\"nav-link nav-link-collapse\" data-toggle=\"collapse\" href=\"#collapseprofile\" data-parent=\"#exampleAccordion\">\n" +
                    "                        <i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Mon profil</span>\n" +
                    "                    </a>\n" +
                    "                    <ul class=\"sidenav-second-level collapse\" id=\"collapseprofile\">\n" +
                    "                        <li>\n" +
                    "                            <a href=\"/ChangePassword\"><i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Changer mot de passe</span>\n" +
                    "                            </a>\n" +
                    "                        </li>\n" +
                    "                        <li>\n" +
                    "                            <a href=\"\"><i class=\"fa fa-fw fa-user\"></i>\n" +
                    "                                <span class=\"nav-link-text\">Modifier informations</span>\n" +
                    "                            </a>\n" +
                    "                        </li>\n" +
                    "\n" +
                    "                    </ul>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Link\">\n" +
                    "                    <a class=\"nav-link\" href=\"/logout\">\n" +
                    "                        <i class=\"fa fa-fw fa-sign-out\"></i>\n" +
                    "                        <span class=\"nav-link-text\">Deconnexion</span>\n" +
                    "                    </a>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "            <ul class=\"navbar-nav sidenav-toggler\">\n" +
                    "                <li class=\"nav-item\">\n" +
                    "                    <a class=\"nav-link text-center\" id=\"sidenavToggler\">\n" +
                    "                        <i class=\"fa fa-fw fa-angle-left\"></i>\n" +
                    "                    </a>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "        </div>\n" +
                    "    </nav>");
            break;

    }
%>

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
            <form method="post">

                <div class="form-group" align="center">
                    <label for="id">Veuillez entrer le nouveau mot de passe</label>
                    <input class="form-control" id="id" name="new-mdp" type="password" placeholder="Nouveau mot de passe">
                </div>
                <div class="form-group" align="center">
                    <input class="form-control" name="confirm-new-mdp" type="password" placeholder="Confirmez nouveau mot de passe">
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
</body>

</html>