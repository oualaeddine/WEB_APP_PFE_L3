<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.views.MyView" %>
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
    <title>Ajouter un logement</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-white">
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
<div class="container" style="margin-top: 10%">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Ajouter un logement</div>
        <div class="card-body">
            <form method="post" action="/AjoutServlet?ajouter=logement">
                <div class="form-group">
                    <div class="form-row">
                        <label for="titreInput">Titre</label>
                        <input class="form-control" id="titreInput" name="titreInput" type="text" aria-describedby="nameHelp" placeholder="Titre">

                    </div>
                    <div class="form-row">
                        <label for="description">Description</label>
                        <input class="form-control" id="description" name="description" type="text" aria-describedby="nameHelp" placeholder="Description">
                    </div>
                    <div class="form-row">
                        <label for="adresse">Adresse</label>
                        <input class="form-control" id="adresse" name="adresse" type="text" aria-describedby="nameHelp" placeholder="Adresse">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="superficie">Superficie</label>
                            <input class="form-control" id="superficie" name="superficie" type="number" placeholder="Superficie">
                        </div>
                        <div class="col-md-6">
                            <label for="region">Region</label>
                            <select class="custom-select" name="region" id="region">
                                <%
                                    LinkedList<Localite> list = new LocaliteDAO().getAll();
                                    for (Localite localite : list) {
                                        out.print("<option value=\""+localite.getId()+"\">"+localite.getNom()+"</option>");
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="nbrPcs">Pièces</label>
                            <input class="form-control" id="nbrPcs" name="nbrPcs" type="number" aria-describedby="emailHelp" placeholder="Nombre de pièces">
                        </div>
                        <div class="col-md">
                            <label for="nbrFcds">Façades</label>
                            <input class="form-control" id="nbrFcds" name="nbrFcds" type="number" aria-describedby="emailHelp" placeholder="Nombre de façades">
                        </div>
                        <div class="col-md">
                            <label for="etage">Etage</label>
                            <input class="form-control" id="etage" name="etage" type="number" aria-describedby="emailHelp" placeholder="Etage">
                        </div>
                        <div class="col-md-1">
                            <label for="jardin">Avec jardin</label>
                            <input name="jardin" type="checkbox" class="form-control" id="jardin" value="jardin" placeholder="Avec jardin">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md">
                            <label for="prix">Prix</label>
                            <input class="form-control" id="prix" name="prix" type="number" aria-describedby="emailHelp" placeholder="Prix">
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <button class="btn btn-primary btn-block" type="submit" value="register">Ajouter</button>
                </div>
            </form>
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