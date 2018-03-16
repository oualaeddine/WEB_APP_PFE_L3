<%@ page import="model.beans.Visite" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.VisitesDao" %>
<%@ page import="model.beans.humans.Agent" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="model.db.daos.ClientDAO" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 23/02/2018
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

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

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark navbar-My fixed-top" id="mainNav">
    <a class="navbar-brand" href="#">Espace Agent</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav scroll-nav navbar-sidenav" id="exampleAccordion">
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                <a class="nav-link" href="/AgentServlet">
                    <i class="fa fa-fw fa-home"></i>
                    <span class="nav-link-text">Accueil</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
                <a class="nav-link" href="/AgentServlet?what=etablirRapport">
                    <i class="fa fa-fw fa-edit"></i>
                    <span class="nav-link-text">Etablir un rapport</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseTables" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-envelope"></i>
                    <span class="nav-link-text">Messages</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseTables">
                    <li>
                        <a href="/AgentServlet?what=AdminsMessages"><i class="fa fa-fw fa-envelope"></i>
                            <span class="nav-link-text">Administration</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=newMessage"><i class="fa fa-fw fa-pencil"></i>
                            <span class="nav-link-text">Messages</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-eye"></i>
                    <span class="nav-link-text">Visites</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseComponents">
                    <li>
                        <a href="/AgentServlet?what=visitesProgrammees"><i class="fa fa-fw fa-calendar"></i>
                            <span class="nav-link-text">Visites Programmées</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=visitesPassees"><i class="fa fa-fw fa-calendar-check"></i>
                            <span class="nav-link-text">Visites Passées</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=visitesAnnulees"><i class="fa fa-fw fa-calendar-times"></i>
                            <span class="nav-link-text">Visites annulées</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseClients" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-users"></i>
                    <span class="nav-link-text">Clients</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseClients">
                    <li>
                        <a href="/AgentServlet?what=listeClients"><i class="fa fa-fw fa-bars"></i>
                            <span class="nav-link-text">Liste des clients</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=myClients"><i class="fa fa-fw fa-gratipay"></i>
                            <span class="nav-link-text">Clients bannis</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=signalerClient"><i class="fa fa-fw fa-ban"></i>
                            <span class="nav-link-text">Signaler un client</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="buildings">
                <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapsebuildings" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-building"></i>
                    <span class="nav-link-text">Logements</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapsebuildings">
                    <li>
                        <a href="/AgentServlet?what=allLogements"><i class="fa fa-fw fa-list"></i>
                            <span class="nav-link-text">Liste des Logements</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=logementsVendus"><i class="fa fa-fw fa-check"></i>
                            <span class="nav-link-text">Logements vendus</span></a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=logementsGeles"><i class="fa fa-fw fa-object-group"></i>
                            <span class="nav-link-text">Logements gelés</span></a>
                    </li>
                </ul>

            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Levels">
                <a class="nav-link nav-link-collapse" data-toggle="collapse" href="#collapseprofile" data-parent="#exampleAccordion">
                    <i class="fa fa-fw fa-user"></i>
                    <span class="nav-link-text">Mon profil</span>
                </a>
                <ul class="sidenav-second-level collapse" id="collapseprofile">
                    <li>
                        <a href="/AgentServlet?what=modifierProfil"><i class="fa fa-fw fa-user-circle"></i>
                            <span class="nav-link-text">Modifier profil</span>
                        </a>
                    </li>
                    <li>
                        <a href="/AgentServlet?what=changePassword"><i class="fa fa-fw fa-lock"></i>
                            <span class="nav-link-text">Changer mot de passe</span>
                        </a>
                    </li>

                </ul>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
                <a class="nav-link" href="/logout">
                    <i class="fa fa-fw fa-sign-out"></i>
                    <span class="nav-link-text">Deconnexion</span>
                </a>
            </li>
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
                <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Etablir rapport</li>
        </ol>
        <div class="card-body">
            <form method="post">
                <div class="form-group">
        <%Agent agent = (Agent) request.getSession().getAttribute("user");%>
                    <label for="agent">Agent responsable de la visite:  </label>
                    <p class="form-control" id="agent"><%out.print(agent.getNom()+" "+agent.getPrenom());%></p>
                </div>
                <div class="form-group">
                    <label >Visite du: </label>
                    <select class="custom-select" name="etatVisite" id="visite">
                        <%
                            LinkedList<Visite> list = new VisitesDao().getAll();
                            for (Visite visite : list){
                                out.print("<option value=\""+visite.id+"\">"+visite.getTime()+"</option>");
                            }%>
                        <option value="validee">Validée</option>
                        <option value="nonvalidee">Non validée</option>
                        <option value="reportee">Reportée</option>
                        <option value="annulee"> Annulée</option>
                    </select>
                </div>
                <div class="form-group">
                    <label >Client visiteur: </label>
                    <p class="form-control"><%out.print(visite.getClient().getNom()+" "+visite.getClient().getPrenom());%></p>
                </div>
                <div class="form-group">
                    <label >Logement: </label>
                    <p class="form-control"><%out.print(visite.getLogement().getAdresse());%></p>
                </div>
                <div class="form-group">
                    <label >Date: </label>
                    <p class="form-control"><%out.print(visite.getTime());%></p>
                </div>
                <div class="form-group">
                    <label for="type">Selectionnez l'état de la visite</label>
                    <select class="custom-select" name="etatVisite" id="type">
                        <option value="validee">Validée</option>
                        <option value="nonvalidee">Non validée</option>
                        <option value="reportee">Reportée</option>
                        <option value="annulee"> Annulée</option>
                    </select>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Soumettre</button>
            </form>
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
