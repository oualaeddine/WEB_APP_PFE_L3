<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="static utils.MyConsts.FOOTER_COPYRIGHT" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.beans.views.table.DataTableRow" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 2/17/2018
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
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
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%out.print(tablesView.getPageTitle() + " | " + tablesView.getDataTable().getDataTableTitle());%></title>
    <!-- Bootstrap core CSS-->
    <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="./vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="./vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="./vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="./vendor/datatables/buttons.dataTables.min.css" rel="stylesheet">
    <link href="./vendor/datatables/jquery.dataTables.min.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="./css/sb-admin.css" rel="stylesheet">
    <!-- fullCalendar -->
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.print.min.css" media="print">
    <link rel="stylesheet" href="./css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-responsive/responsive.dataTables.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-select/select.dataTables.min.css">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->

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

                        <% LinkedList<DataTableRow> list = tablesView.getDataTable().getTableData();
                            for (DataTableRow tableRow : list) {
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
    <%--Modal assigner region--%>
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Sélectionner la région</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=assignation" id="assignerRegionForm">

                        <input id="agentId" name="agentInput" type="hidden">
                        <div class="form-group">
                            <label for="selectRegion">Sélectionner région</label>
                            <select class="custom-select" id="selectRegion" name="selectRegion">
                                <%LinkedList<Localite> localites = new LocaliteDAO().getAll();%>
                                <%
                                    for (Localite localite : localites) {
                                        out.print("<option value=\"" + localite.getId() + "\">" + localite.getNom() + "</option>\n");
                                    }
                                %>
                            </select>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="assignerRegionForm">Confirmer</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

    <%--Modal signaler client--%>
    <div id="signalerModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Motif</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=signalement" id="signalerForm">

                        <input id="clientSignale" name="clientInput" type="hidden">
                        <div class="form-group">
                            <label for="motif">Veuillez entrer le motif de votre signalement</label>
                            <textarea id="motif" rows="4" cols="50" name="comment" form="signalerForm" size="100">

                            </textarea>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="signalerForm">Valider</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
    <%--Modal approuver employe--%>
    <div id="approuverModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=approuvement" id="approuverForm">


                        <input id="employeApprouve" name="employeApprouve" type="hidden">
                        <div class="form-group">
                            <label for="userTypeInput">Type d'employé</label>
                            <select class="custom-select" name="userTypeInput" id="userTypeInput">
                                <option value="agent">Agent</option>
                                <option value="operateur">Operateur</option>
                                <option value="responsable_ventes">Responsable ventes</option>
                                <%
                                    if (userType == UserType.SU) {
                                        out.print("<option value=\"admin\">Admin</option>");
                                    }
                                %>
                            </select>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="approuverForm">Valider</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>
    <%--Modal suspendre/réintegrer employé--%>
    <div id="suspendreModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=suspend" id="suspendreForm">

                        <input id="employeSuspendu" name="employeSuspendu" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir suspendre/réintegrer cet employé ?</label>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="suspendreForm">Valider</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>
    <%--Modal Geler/Dégeler logement--%>
    <div id="gelerModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=gel" id="gelerForm">

                        <input id="logementGele" name="logementGele" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir geler/dégeler ce logement ?</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="gelerForm">Valider</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <%--Modal Modifier/Annuler visite--%>
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
                            <label for="motif">Veuillez entrer le type de la modification</label>
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

    <input type="hidden" name="selectedRowId" id="selectedRowId">

    <%--Modal Etablir Rapport--%>
    <div id="etablirRapportModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Envoyer rapport</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/NewRapport" id="etablirRapportForm">

                        <input id="visiteRapport" name="visiteRapport" type="hidden">
                        <label for="etatClient">Cochez cette case si le client s'est présenté</label>
                        <input type="checkbox" name="etatClient" id="etatClient" value="0">
                        <div class="form-group" id="ifPresent" style="display:none">
                            <div class="form-group">
                                <div class="form-row">
                                    <label for="avis">Avis du client</label>
                                    <select name="avis" id="avis" class="form-control">
                                        <option value="positif"> Positif</option>
                                        <option value="negatif"> Négatif</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="commentaire">Commentaire:</label>
                                    <textarea class="form-control" name="commentaire" id="commentaire" rows="5"
                                              placeholder="Entrez le commentaire ici"></textarea>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="etablirRapportForm">Soumettre</button>

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
    <%--Modal Bannir/Retablir client--%>
    <div id="bannirModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=ban" id="bannirForm">

                        <input id="clientBanni" name="clientBanni" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir bannir/rétablir ce client ?</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="bannirForm">Valider</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <%--Modal Annuler vente--%>
    <div id="annulerVenteModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/AjoutServlet?ajouter=annulationVente" id="annulerVenteForm">

                        <input id="venteAnnulee" name="venteAnnulee" type="hidden">
                        <div class="form-group">
                            <label>Êtes vous sur de vouloir annuler cette vente ?</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="annulerVenteForm">Oui</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <%--Employe details modal--%>
    <div id="detailsEmployeModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'employé</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="table">
                        <tr>
                            <td>Nom:</td>
                        </tr>
                        <tr>
                            <td>Prénom:</td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                        </tr>
                        <tr>
                            <td>Numéro de téléphone:</td>
                        </tr>
                        <tr>
                            <td>Adresse email:</td>
                        </tr>
                        <tr>
                            <td>Nom d'utilisateur:</td>
                        </tr>
                        <tr>
                            <td>Role:</td>
                        </tr>
                        <tr>
                            <td>Ajouté le:</td>
                        </tr>
                        <tr>
                            <td>Ajouté par:</td>
                        </tr>
                        <tr>
                            <td>Approuvé:</td>
                        </tr>
                        <tr>
                            <td>Suspendu:</td>
                        </tr>

                    </table>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-info btn-lg" type="submit" form="annulerVenteForm">Oui</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <%--Confirmer appel modal--%>
    <div id="confirmerAppelModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Confirmer appel</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/DashboardServlet?what=confirmerAppel" id="confirmerAppelForm">

                        <input id="appelConfirme" name="appelConfirme" type="hidden">
                        <label for="confirmationAppel">Cochez cette case si le client désire confirmer sa visite</label>
                        <input type="checkbox" name="confirmationAppel" id="confirmationAppel" value="0">
                        <div class="form-group" id="ifConfirme" style="display:none">
                            <div class="form-group">
                                <div class="col-md-12 col-xs-12 register-blocks">
                                    <h2>Inscription : </h2>
                                    <div class="form-group">
                                        <label for="nomInput">Nom</label>
                                        <input type="text" class="form-control" id="nomInput" name="nomInput">

                                        <label for="prenomInput">Prenom</label>
                                        <input type="text" class="form-control" id="prenomInput" name="prenomInput">

                                        <label for="dateNaissance">Date de naissance</label>
                                        <input type="date" class="form-control" id="dateNaissance" name="dateNaissance">
                                    </div>
                                    <div class="form-group">
                                        <label for="emailInput">Email</label>
                                        <input type="text" class="form-control" id="emailInput" name="emailInput">

                                        <label for="inputTel">Numéro de téléphone</label>
                                        <input type="text" class="form-control" id="inputTel" name="inputTel">

                                        <label for="adresseInput">Adresse</label>
                                        <input type="text" class="form-control" id="adresseInput" name="adresseInput">

                                    </div>
                                    <div class="form-group">
                                        <label for="usernameInput">Nom d'utilisateur</label>
                                        <input type="text" class="form-control" id="usernameInput" name="usernameInput">

                                        <label for="passwordInput">Mot de passe</label>
                                        <input type="password" class="form-control" id="passwordInput"
                                               name="passwordInput">
                                    </div>
                                    <div class="text-center">

                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" form="confirmerAppelForm">Soumettre</button>

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details appels modal--%>
    <div id="appelDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'appel</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table id="example" class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="appelIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Numéro de téléphone:</td>
                            <td><span id="appelNumeroDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Logement:</td>
                            <td><span id="appelLogementDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date:</td>
                            <td><span id="appelDateDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Heure:</td>
                            <td><span id="appelHorraireDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Confirmé:</td>
                            <td><span id="appelIsConfirmeDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details agents modal--%>
    <div id="agentDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'employé</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="agentIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom:</td>
                            <td><span id="agentNomDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                            <td><span id="agentDateNaissanceDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="agentsAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Téléphone:</td>
                            <td><span id="agentTelDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="agentEmailDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Localité:</td>
                            <td><span id="agentLocaliteDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Ajouté le:</td>
                            <td><span id="agentDateAjoutDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details res ventes modal--%>
    <div id="resVenteDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'employé</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="resVenteIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom:</td>
                            <td><span id="resVenteNomDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                            <td><span id="resVenteDateNaissanceDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="resVentesAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Téléphone:</td>
                            <td><span id="resVenteTelDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="resVenteEmailDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Suspendu:</td>
                            <td><span id="resVenteEtatDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Approuvé le:</td>
                            <td><span id="resVenteDateAjoutDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details opérateur modal--%>
    <div id="operateurDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'employé</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="operateurIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom:</td>
                            <td><span id="operateurNomDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                            <td><span id="operateurDateNaissanceDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="operateursAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Téléphone:</td>
                            <td><span id="operateurTelDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="operateurEmailDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Ajouté le:</td>
                            <td><span id="operateurDateAjoutDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details Admin modal--%>
    <div id="adminDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de l'employé</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="adminIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom:</td>
                            <td><span id="adminNomDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                            <td><span id="adminDateNaissanceDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="adminsAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Téléphone:</td>
                            <td><span id="adminTelDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="adminEmailDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Role:</td>
                            <td><span id="adminRoleDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Ajouté le:</td>
                            <td><span id="adminDateAjoutDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details signalement modal--%>
    <div id="signalementDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails du signalement</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>Nom et prénom du plaignant:</td>
                            <td><span id="plaignantDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Sujet de la plainte:</td>
                            <td><span id="sujetPlainteDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Motif:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><span id="signalementMotifDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details vente modal--%>
    <div id="venteDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de la vente</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="venteIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom du client:</td>
                            <td><span id="venteClientDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Logement:</td>
                            <td><span id="venteLogementDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date:</td>
                            <td><span id="venteDateDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Montant restant à payer:</td>
                            <td><span id="venteRestantDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Etat:</td>
                            <td><span id="venteEtatDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details logements modal--%>
    <div id="logementDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails du logement</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="logementIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Titre:</td>
                            <td><span id="logementTitreDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><span id="logementDescriptionDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Superficie:</td>
                            <td><span id="logementSuperficieDetails"></span> m2</td>
                        </tr>
                        <tr>
                            <td>Nombre de pièces:</td>
                            <td><span id="logementNbrPieces"></span></td>
                        </tr>
                        <tr>
                            <td>Nombre de salles de bain:</td>
                            <td><span id="logementNbrSdb"></span></td>
                        </tr>
                        <tr>
                            <td>Avec jardin:</td>
                            <td><span id="logementJardinDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Avec garage:</td>
                            <td><span id="logementGarageDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Avec sous-sol:</td>
                            <td><span id="logementSousSolDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Meublé:</td>
                            <td><span id="logementMeubleDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="logementAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Localite:</td>
                            <td><span id="logementLocaliteDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Prix:</td>
                            <td><span id="logementPrixDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Etage:</td>
                            <td><span id="logementEtageDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Gelé:</td>
                            <td><span id="logementEtatDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Latitude:</td>
                            <td><span id="logementLatitudeDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Longitude:</td>
                            <td><span id="logementLongitudeDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details visite modal--%>
    <div id="visiteDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de la visite</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID de la visite:</td>
                            <td><span id="visiteIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Logement:</td>
                            <td><span id="visiteLogementDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Agent:</td>
                            <td><span id="visiteAgentDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Client:</td>
                            <td><span id="visiteClientDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date et heure:</td>
                            <td><span id="visiteTimestampDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Etat:</td>
                            <td><span id="visiteEtatDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details client modal--%>
    <div id="clientDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Détails de la visite</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table class="display nowrap" style="width:100%">

                        <tr>
                            <td>ID:</td>
                            <td><span id="clientIdDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Nom et prénom:</td>
                            <td><span id="clientNomDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Date de naissance:</td>
                            <td><span id="clientDateDeNaissanceDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Téléphone:</td>
                            <td><span id="clientTelDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td><span id="clientAdresseDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><span id="clientEmailDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Etat:</td>
                            <td><span id="clientEtatDetails"></span></td>
                        </tr>
                        <tr>
                            <td>Inscription le:</td>
                            <td><span id="clientDateAddedDetails"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <%--Details versements par vente--%>
    <div id="versementsForVenteDetails" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Versements</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <table id="versementsForVenteTable" class="display nowrap" style="width:100%">

                        <%--<tr><td>ID: </td><td><span id="versementForVenteIdDetails"></span></td></tr>--%>
                        <%--<tr><td>Montant: </td><td><span id="versementForVenteMontantDetails"></span></td></tr>--%>
                        <%--<tr><td>Date: </td><td><span id="versementForVenteDateDetails"></span></td></tr>--%>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="./vendor/jquery/jquery.min.js"></script>
    <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="./vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="./vendor/datatables/jquery.dataTables.js"></script>
    <script src="./vendor/datatables/dataTables.bootstrap4.js"></script>

    <script src="./vendor/datatables/dataTables.buttons.min.js"></script>
    <script src="./vendor/datatables/buttons.flash.min.js"></script>
    <script src="./vendor/datatables/jszip.min.js"></script>
    <script src="./vendor/datatables/pdfmake.min.js"></script>
    <script src="./vendor/datatables/vfs_fonts.js"></script>
    <script src="./vendor/datatables/buttons.html5.min.js"></script>
    <script src="./vendor/datatables/buttons.print.min.js"></script>
    <script src="./js/bootstrapValidator.min.js"></script>
    <script src="./programmerVisite/assets/datatables-responsive/dataTables.responsive.min.js"></script>
    <script src="./programmerVisite/assets/datatables-select/dataTables.select.min.js"></script>


    <!-- Custom scripts for all pages-->
    <script src="./js/sb-admin.min.js"></script>
    <script>

        $('#etatClient').change(function () {
            $(this).next('#ifPresent').toggle();
        });
        $('#confirmationAppel').change(function () {
            $(this).next('#ifConfirme').toggle();
        });


        var table = $('#dataTable').DataTable({
            processing: true,
            select: true,
            dom: 'Bfrtip',
            buttons: [
                'copy',
                'csv',
                'excel',
                {
                    extend: 'pdfHtml5',
                    messageTop: 'Ce fichier est créé pour : <%out.print(employe.getFullName()+"("+employe.getUserType()+")");%>'
                },
                {
                    extend: 'print',
                    messageTop: 'Ce fichier est créé pour : <%out.print(employe.getFullName()+"("+employe.getUserType()+")");%>'
                }
            ]

        });


        function afficherDetailsVersementsForVente(rowData) { // ça me fait chier
            $.ajax({
                type: 'GET',
                url: '/api/versementApi?action=getByVente&venteId=' + rowData[0][0],
                dataType: 'json',
                success: function (result) {

                    var jsonData = JSON.parse(result);
                    for (var i = 0; i < jsonData.length; i++) {
                        // document.getElementById("versementsForVenteTable").innerHTML += "<tr><td>a</td><td>b</td><tr>";
                        console.log("aaaaaaa");
                    }
                }
            });
            $("#versementsForVenteDetails").modal({
                show: true
            });
        }

        function afficherDetailsadmin(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/AgentApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var employe = JSON.parse(result);
                    document.getElementById("adminIdDetails").innerHTML = employe.id;
                    document.getElementById("adminNomDetails").innerHTML = employe.nom + ' ' + employe.prenom;
                    document.getElementById("adminTelDetails").innerHTML = employe.tel;
                    document.getElementById("adminsAdresseDetails").innerHTML = employe.adresse;
                    document.getElementById("adminEmailDetails").innerHTML = employe.email;
                    document.getElementById("adminDateNaissanceDetails").innerHTML = employe.dateNaissance;
                    document.getElementById("adminRoleDetails").innerHTML = rowData[0][6];
                    document.getElementById("adminDateAjoutDetails").innerHTML = employe.dateAdded;
                }
            });
            $("#adminDetails").modal({
                show: true
            });
        } // ok

        function afficherDetailsAppels(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/AppelsApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var appel = JSON.parse(result);
                    var horraire;
                    switch (appel.visite.horraire) {
                        case 1:
                            horraire = '08:00 - 10:00';
                            break;
                        case 2:
                            horraire = '10:00 - 12:00';
                            break;
                        case 3:
                            horraire = '12:00 - 14:00';
                            break;
                        case 4:
                            horraire = '14:00 - 16:00';
                            break;
                    }
                    document.getElementById("appelIdDetails").innerHTML = appel.id;
                    document.getElementById("appelNumeroDetails").innerHTML = appel.numeroTel;
                    document.getElementById("appelLogementDetails").innerHTML = appel.visite.logement.titre;
                    document.getElementById("appelDateDetails").innerHTML = appel.visite.timestamp;
                    document.getElementById("appelHorraireDetails").innerHTML = horraire;
                    document.getElementById("appelIsConfirmeDetails").innerHTML = appel.isConfirmed;
                }
            });
            $("#appelDetails").modal({
                show: true
            });
        } //ok

        function afficherDetailsAgent(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/AgentApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var employe = JSON.parse(result);
                    document.getElementById("agentIdDetails").innerHTML = employe.id;
                    document.getElementById("agentNomDetails").innerHTML = employe.nom + ' ' + employe.prenom;
                    document.getElementById("agentTelDetails").innerHTML = employe.tel;
                    document.getElementById("agentsAdresseDetails").innerHTML = employe.adresse;
                    document.getElementById("agentEmailDetails").innerHTML = employe.email;
                    document.getElementById("agentDateNaissanceDetails").innerHTML = employe.dateNaissance;
                    document.getElementById("agentLocaliteDetails").innerHTML = rowData[0][6];
                    document.getElementById("agentDateAjoutDetails").innerHTML = employe.dateAdded;
                }
            });

            $("#agentDetails").modal({
                show: true
            });
        } //ok

        function afficherDetailsOperateur(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/AgentApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var employe = JSON.parse(result);
                    document.getElementById("operateurIdDetails").innerHTML = employe.id;
                    document.getElementById("operateurNomDetails").innerHTML = employe.nom + ' ' + employe.prenom;
                    document.getElementById("operateurTelDetails").innerHTML = employe.tel;
                    document.getElementById("operateursAdresseDetails").innerHTML = employe.adresse;
                    document.getElementById("operateurEmailDetails").innerHTML = employe.email;
                    document.getElementById("operateurDateNaissanceDetails").innerHTML = employe.dateNaissance;
                    document.getElementById("operateurDateAjoutDetails").innerHTML = employe.dateAdded;
                }
            });

            $("#operateurDetails").modal({
                show: true
            });
        } //ok

        function afficherDetailsResVente(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/AgentApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var employe = JSON.parse(result);
                    document.getElementById("resVenteIdDetails").innerHTML = employe.id;
                    document.getElementById("resVenteNomDetails").innerHTML = employe.nom + ' ' + employe.prenom;
                    document.getElementById("resVenteTelDetails").innerHTML = employe.tel;
                    document.getElementById("resVentesAdresseDetails").innerHTML = employe.adresse;
                    document.getElementById("resVenteEmailDetails").innerHTML = employe.email;
                    document.getElementById("resVenteDateNaissanceDetails").innerHTML = employe.dateNaissance;
                    document.getElementById("resVenteEtatDetails").innerHTML = employe.isSuspended;
                    document.getElementById("resVenteDateAjoutDetails").innerHTML = employe.dateAdded;
                }
            });
            $("#resVenteDetails").modal({
                show: true
            });
        } //ok

        function afficherDetailsSignalement(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/clientApi?action=getSignalementById&id=' + rowData[0][0],
                success: function (result) {
                    var signalement = JSON.parse(result);
                    document.getElementById("plaignantDetails").innerHTML = signalement.plaignant.nom + ' ' + signalement.plaignant.prenom;
                    document.getElementById("sujetPlainteDetails").innerHTML = signalement.client.nom + ' ' + signalement.client.prenom;
                    document.getElementById("signalementMotifDetails").innerHTML = signalement.motif;
                }
            });
            $("#signalementDetails").modal({
                show: true
            });
        } // ok

        function afficherDetailsVente(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/venteApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var vente = JSON.parse(result);
                    document.getElementById("venteIdDetails").innerHTML = vente.id;
                    document.getElementById("venteClientDetails").innerHTML = vente.client.nom + ' ' + vente.client.prenom;
                    document.getElementById("venteLogementDetails").innerHTML = 'ID:' + vente.logement.id + ' | Titre: ' + vente.logement.titre;
                    document.getElementById("venteDateDetails").innerHTML = vente.date;
                    document.getElementById("venteRestantDetails").innerHTML = rowData[0][4] + ' DA';
                    document.getElementById("venteEtatDetails").innerHTML = vente.etatVente;
                }
            });
            $("#venteDetails").modal({
                show: true
            });
        } // ok

        function afficherDetailsLogement(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/logementApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var logement = JSON.parse(result);
                    document.getElementById("logementIdDetails").innerHTML = logement.id;
                    document.getElementById("logementTitreDetails").innerHTML = logement.titre;
                    document.getElementById("logementDescriptionDetails").innerHTML = logement.description;
                    document.getElementById("logementSuperficieDetails").innerHTML = logement.superficie;
                    document.getElementById("logementNbrPieces").innerHTML = logement.nbrPieces;
                    document.getElementById("logementNbrSdb").innerHTML = logement.nbrSdb;
                    document.getElementById("logementJardinDetails").innerHTML = logement.avecJardin;
                    document.getElementById("logementGarageDetails").innerHTML = logement.avecGarage;
                    document.getElementById("logementSousSolDetails").innerHTML = logement.avecSousSol;
                    document.getElementById("logementMeubleDetails").innerHTML = logement.meubles;
                    document.getElementById("logementAdresseDetails").innerHTML = logement.adresse;
                    document.getElementById("logementLocaliteDetails").innerHTML = logement.localite.nom;
                    document.getElementById("logementPrixDetails").innerHTML = logement.prix + ' DA';
                    document.getElementById("logementEtageDetails").innerHTML = logement.etage;
                    document.getElementById("logementEtatDetails").innerHTML = logement.isGele;
                    document.getElementById("logementLatitudeDetails").innerHTML = logement.location.latitude;
                    document.getElementById("logementLongitudeDetails").innerHTML = logement.location.longitude;
                }
            });
            $("#logementDetails").modal({
                show: true
            });
        } // ok

        function afficherDetailsVisite(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/visiteApi?action=getById&id=' + rowData[0][0],
                success: function (result) {
                    var visite = JSON.parse(result);
                    var horraire;
                    switch (visite.horraire) {
                        case 1:
                            horraire = '08:00 - 10:00';
                            break;
                        case 2:
                            horraire = '10:00 - 12:00';
                            break;
                        case 3:
                            horraire = '12:00 - 14:00';
                            break;
                        case 4:
                            horraire = '14:00 - 16:00';
                            break;
                    }
                    document.getElementById("visiteIdDetails").innerHTML = visite.id;
                    document.getElementById("visiteLogementDetails").innerHTML = 'ID: ' + visite.logement.id + ' | Titre: ' + visite.logement.titre;
                    document.getElementById("visiteAgentDetails").innerHTML = visite.agent.nom + ' ' + visite.agent.prenom;
                    document.getElementById("visiteClientDetails").innerHTML = visite.client.nom + ' ' + visite.client.prenom;
                    document.getElementById("visiteTimestampDetails").innerHTML = visite.timestamp + ' | ' + horraire;
                    document.getElementById("visiteEtatDetails").innerHTML = visite.etatVisite;
                }
            });
            $("#visiteDetails").modal({
                show: true
            });
        } // ok

        function afficherDetailsClient(rowData) {
            $.ajax({
                type: 'GET',
                url: '/api/clientApi?action=getById&clientId=' + rowData[0][0],
                success: function (result) {
                    var client = JSON.parse(result);
                    document.getElementById("clientIdDetails").innerHTML = client.id;
                    document.getElementById("clientNomDetails").innerHTML = client.nom + ' ' + client.prenom;
                    document.getElementById("clientTelDetails").innerHTML = client.tel;
                    document.getElementById("clientAdresseDetails").innerHTML = client.adresse;
                    document.getElementById("clientEmailDetails").innerHTML = client.email
                    document.getElementById("clientDateDeNaissanceDetails").innerHTML = client.dateNaissance;
                    document.getElementById("clientEtatDetails").innerHTML = client.isBanned ? "Non banni" : "Banni";
                    document.getElementById("clientDateAddedDetails").innerHTML = client.dateAdded;
                }
            });
            $("#clientDetails").modal({
                show: true
            });
        } // ok

        table.on('select', function (e, dt, type, indexes) {
            var rowData = table.rows(indexes).data().toArray();

            var page = '<%out.print(currentPage);%>';
            switch (page) {
                case 'VERSEMENTS_FOR_VENTE':
                    afficherDetailsVersementsForVente(rowData);
                    break;
                case 'APPELS':
                    afficherDetailsAppels(rowData);
                    break;
                case 'APPELS_CONFIRMES':
                    afficherDetailsAppels(rowData);
                    break;
                case 'APPELS_NON_CONFIRMES':
                    afficherDetailsAppels(rowData);
                    break;
                case 'AGENTS':
                    afficherDetailsAgent(rowData);
                    break;
                case 'RESPONSABLES_VENTES':
                    afficherDetailsResVente(rowData);
                    break;
                case 'OPERATEURS':
                    afficherDetailsOperateur(rowData);
                    break;
                case 'ADMINS':
                    afficherDetailsadmin(rowData);
                    break;
                case 'SIGNALEMENT':
                    afficherDetailsSignalement(rowData);
                    break;
                case 'ANNULER_VENTE':
                    afficherDetailsVente(rowData);
                    break;
                case 'VENTES_ANNULEES':
                    afficherDetailsVente(rowData);
                    break;
                case 'VENTES':
                    afficherDetailsVente(rowData);
                    break;
                case 'VENTES_EN_COURS':
                    afficherDetailsVente(rowData);
                    break;
                case 'CONFIRMED_VENTES':
                    afficherDetailsVente(rowData);
                    break;
                case 'LOGEMENTS':
                    afficherDetailsLogement(rowData);
                    break;
                case 'LOGEMENTS_VENDUS':
                    afficherDetailsLogement(rowData);
                    break;
                case 'FROZEN_LOGEMENTS':
                    afficherDetailsLogement(rowData);
                    break;
                case 'GELER_LOGEMENT':
                    afficherDetailsLogement(rowData);
                    break;
                case 'LOGEMENTS_FOR_USER':
                    afficherDetailsLogement(rowData);
                    break;
                case 'LOGEMENTS_NON_VENDUS':
                    afficherDetailsLogement(rowData);
                    break;
                case 'VISITES':
                    afficherDetailsVisite(rowData);
                    break;
                case 'REPORTED_VISITES':
                    afficherDetailsVisite(rowData);
                    break;
                case 'CANCELED_VISITES':
                    afficherDetailsVisite(rowData);
                    break;
                case 'AGENT_VISITES':
                    afficherDetailsVisite(rowData);
                    break;
                case 'PROGRAMMED_VISITES':
                    afficherDetailsVisite(rowData);
                    break;
                case 'MY_CANCELED_VISITS':
                    afficherDetailsVisite(rowData);
                    break;
                case 'MODIFIER_VISITE':
                    afficherDetailsVisite(rowData);
                    break;
                case 'MY_PASSED_VISITS':
                    afficherDetailsVisite(rowData);
                    break;
                case 'CLIENTS':
                    afficherDetailsClient(rowData);
                    break;
                case 'CLIENTS_FOR_USER':
                    afficherDetailsClient(rowData);
                    break;
                case 'BANNED_CLIENTS':
                    afficherDetailsClient(rowData);
                    break;
                case 'BANNIR_CLIENT':
                    afficherDetailsClient(rowData);
                    break;
                case 'CLIENTS_FOR_AGENT':
                    afficherDetailsClient(rowData);
                    break;
                case 'SIGNALER_CLIENT':
                    afficherDetailsClient(rowData);
                    break;
            }


        });


        function getConfirmedAppel(idTaaLAppelConfirme) {
            document.getElementById("appelConfirme").value = idTaaLAppelConfirme;
        }

        function getCanceledVente(idTaaLaventeAnnulee) {
            document.getElementById("venteAnnulee").value = idTaaLaventeAnnulee;
        }

        function getBannedClientId(idTaaLBannedClient) {
            document.getElementById("clientBanni").value = idTaaLBannedClient;
        }

        function getVisiteTaaLrapport(idTaaLaVisite) {
            document.getElementById("visiteRapport").value = idTaaLaVisite;

        }

        function getAgentId(idTaaLagent) {
            document.getElementById("agentId").value = idTaaLagent;
        }

        function getClientId(idTaaLClient) {
            document.getElementById("clientSignale").value = idTaaLClient;

        }

        function getApprovedId(idTaaLEmploye) {
            document.getElementById("employeApprouve").value = idTaaLEmploye;
        }

        function getSuspendedId(idTaaLemploye) {
            document.getElementById("employeSuspendu").value = idTaaLemploye;
        }

        function getLogementGeleId(idTaaLeLogement) {
            document.getElementById("logementGele").value = idTaaLeLogement;
        }

        function getVisiteModifieeId(idtaalavisite, idtaalogement, idtaalaregion, idtaalclient) {

            document.getElementById("visiteModifiee").value = idtaalavisite;
            document.getElementById("visiteId").value = idtaalavisite;
            document.getElementById("clientId").value = idtaalclient;
            document.getElementById("logementId").value = idtaalogement;
            document.getElementById("regionId").value = idtaalaregion;
        }

    </script>

    <!-- fullCalendar -->
    <script src="./programmerVisite/assets/moment/moment.js"></script>
    <script src="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.js"></script>
    <script src="./programmerVisite/assets/fullcalendar/dist/locale/fr.js"></script>
    <script>


        table.on('select', function (e, dt, type, indexes) {
            var rowData = table.rows(indexes).data().toArray();
            $('#selectedRowId').val(rowData[0][0]);
        });


        function post(path, params, method) {
            method = method || "post"; // Set method to post by default if not specified.

            // The rest of this code assumes you are not using a library.
            // It can be made less wordy if you use one.
            var form = document.createElement("form");
            form.setAttribute("method", method);
            form.setAttribute("action", path);

            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", key);
                    hiddenField.setAttribute("value", params[key]);

                    form.appendChild(hiddenField);
                }
            }

            document.body.appendChild(form);
            form.submit();
        }

        var calendar = $('#calendar').fullCalendar({

                themeSystem: 'standard',
                defaultView: 'agendaWeek',

                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: ''
                },
                title: "choisissez une date",
                // defaultDate: '2018-03-12',
                weekNumbers: false,
                navLinks: false, // can click day/week names to navigate views
                editable: false,
                eventLimit: true, // allow "more" link when too many events
                hiddenDays: [6, 7], // hide Tuesdays and Thursdays
                selectable: true,
                unselectAuto: false,
                businessHours: {
                    // days of week. an array of zero-based day of week integers (0=Sunday)
                    dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday

                    start: '8:00', // a start time (10am in this example)
                    end: '16:00' // an end time (6pm in this example)
                },
                // days of week. an array of zero-based day of week integers (0=Sunday)
                dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday
                start: '8:00:00', // a start time (10am in this example)
                end: '16:00:00', // an end time (6pm in this example)
                minTime: '08:00:00',
                maxTime: '16:00:00',
                eventSources: [
                    /*    // your event source
                        {
                            url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
                            color: 'red',    // an option!
                            textColor: 'black'  // an option!
                        }*/

                    // any other sources...
                ],
                eventColor: '#378006',
                displayEventTime: false,
                slotDuration: "02:00:00",
                eventClick: function (calEvent, jsEvent, view) {
                    return false;
                },
                select: function (startDate, endDate) {
                    $('#dateVisite').val(startDate.format());
                }
            })
        ;

        function initCalendar(idLogement) {
            var events = {
                url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
                color: 'red',    // an option!
                textColor: 'black'  // an option!
            };
            calendar.fullCalendar('removeEventSources');
            calendar.fullCalendar('addEventSource', events);
            calendar.fullCalendar('refetchEvents');
        }


        function modifierVisite() {
            var action = $('#action option:selected').val();
            var params;

            console.log("action = " + action);

            if (action == 1) {
                params = {
                    visiteId: $('#visiteModifiee').val(),
                    action: 'rapport',
                    etatVisite: 'annulee'
                };
                console.log("ani f 1");

                post('/NewRapport', params, 'GET');
            }
            if (action == 2) {
                params = {
                    clientId: $('#clientId').val(),
                    visiteId: $('#visiteModifiee').val(),
                    logementId: $('#logementId').val(),
                    regionId: $('#regionId').val(),
                    //    date: $('#newDate').val(),
                    // action: 'rapport',
                    etatVisite: 'reportee'
                };

                console.log("ani f 2");
                post('/modifierVisiteServlet', params, 'GET');
            }
        }

        $('#action').on('change', function () {
            if ($('#action option:selected').val() === 1)
                initCalendar($('#selectedRowId').val())
        });


    </script>
</div>
</body>

</html>
