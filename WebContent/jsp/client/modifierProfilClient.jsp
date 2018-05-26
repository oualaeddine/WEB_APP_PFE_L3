<%@ page import="control.servlets.MyServlet" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="control.statistics.globales.RapportsStats" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="model.db.daos.EmployeDAO" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 4/3/2018
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    LogementsStats logementsStats = new LogementsStats();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERITP | Accueil</title>
    <meta name="description" content="GARO is a real-estate template">
    <meta name="author" content="Kimarotec">
    <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

    <!-- Place favicon.ico  the root directory -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../../assets_client/css/normalize.css">

    <link rel="stylesheet" href="../../assets_client/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets_client/css/fontello.css">
    <link href="../../assets_client/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../../assets_client/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
    <link href="../../assets_client/css/animate.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../../assets_client/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../vendor/bootstrap_client/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets_client/css/icheck.min_all.css">
    <link rel="stylesheet" href="../../assets_client/css/price-range.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.carousel.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.theme.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.transitions.css">
    <link rel="stylesheet" href="../../assets_client/css/style.css">
    <link rel="stylesheet" href="../../assets_client/css/responsive.css">


</head>
<body>
<div id="include_html"></div>
<%
    Client client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
%>
<div class="register-area" style="background-color: rgb(249, 249, 249);">
    <div class="container center-block">

        <div class="col-md-6 ">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 register-blocks">
                    <h2>Modifier mes informations : </h2>
                    <form action="/loginsignup" method="post">
                        <input type="hidden" class="form-control" name="tag" value="edit">

                        <div class="form-group">
                            <label for="nomInput">Nom</label>
                            <input type="text" class="form-control" id="nomInput" name="nomInput"
                                   value="<%out.print(client.getNom());%>">

                            <label for="prenomInput">Prenom</label>
                            <input type="text" class="form-control" id="prenomInput" name="prenomInput"
                                   value="<%out.print(client.getPrenom());%>">

                            <label for="dateNaissance">Prenom</label>
                            <input type="date" class="form-control" id="dateNaissance" name="dateNaissance"
                                   value="<%out.print(client.getDateNaissance());%>">
                        </div>
                        <div class="form-group">
                            <label for="emailInput">Email</label>
                            <input type="text" class="form-control" id="emailInput" name="emailInput"
                                   value="<%out.print(client.getEmail());%>">

                            <label for="inputTel">Numéro de téléphone</label>
                            <input type="text" class="form-control" id="inputTel" name="inputTel"
                                   value="<%out.print(client.getTel());%>">

                            <label for="adresseInput">Adresse</label>
                            <input type="text" class="form-control" id="adresseInput" name="adresseInput"
                                   value="<%out.print(client.getAdresse());%>">

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="../../assets_client/js/modernizr-2.6.2.min.js"></script>

<script src="../../assets_client/js/jquery-1.10.2.min.js"></script>
<script src="../../vendor/bootstrap_client/js/bootstrap.min.js"></script>
<script src="../../assets_client/js/bootstrap-select.min.js"></script>
<script src="../../assets_client/js/bootstrap-hover-dropdown.js"></script>

<script src="../../assets_client/js/easypiechart.min.js"></script>
<script src="../../assets_client/js/jquery.easypiechart.min.js"></script>

<script src="../../assets_client/js/owl.carousel.min.js"></script>

<script src="../../assets_client/js/wow.js"></script>

<script src="../../assets_client/js/icheck.min.js"></script>
<script src="../../assets_client/js/price-range.js"></script>

<script src="../../assets_client/js/main.js"></script>
<script>
    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });
</script>
</body>
</html>
