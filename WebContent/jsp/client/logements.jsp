<%@ page import="control.system.managers.AuthManager" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.LogementDAO" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="control.servlets.MyServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 27/04/2018
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));%>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HCH Immobilier | Nos logements</title>
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

<!-- Body content -->


<div id="include_html"></div>
<div class="content-area recent-property" style="background-color: #FCFCFC; padding-bottom: 55px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                <!-- /.feature title -->
                <h2>Nos logements</h2>
                <p>Choisissez, Visitez, Achetez !</p>
            </div>
        </div>
        <div class="row">
            <div class="property-th">
                <%
                    LinkedList<Logement> logements = new LogementDAO().getNonVendus();
                    Client loggedIdClient = new Client();
                    String href = "data-toggle=\"modal\" data-target=\"#loginRequiredModal\"";
                    if (isLoggedIn) {
                        loggedIdClient = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
                    }
                    for (Logement logement : logements) {
                        if (isLoggedIn) {
                            href = "href=\"/ProgrammerVisiteClient?logementId=" + logement.getId() + "&region=" + logement.getLocalite().getId() + "&clientId=" + loggedIdClient.getId() + "\"";
                        }
                        out.print("<div class=\"col-sm-6 col-md-3 p0\">\n" +
                                "                    <div class=\"box-two proerty-item\">\n" +
                                "                        <div class=\"item-thumb\">\n" +
                                "                            <a " + href + " ><img src=\"../../assets_client/img/demo/property-1.jpg\"></a>\n" +
                                "                        </div>\n" +
                                "                        <div class=\"item-entry overflow\">\n" +
                                "                            <h5><a " + href + ">" + logement.getTitre() + " </a></h5>\n" +
                                "                            <div class=\"dot-hr\"></div>\n" +
                                "                            <span class=\"pull-left\"><b>Area :</b> " + logement.getSuperficie() + "m2</span>\n" +
                                "                            <span class=\"proerty-price pull-right\">" + logement.getPrix() + " DZD</span>\n" +
                                "                        </div>\n" +
                                "                    </div>\n" +
                                "                </div>");
                    }
                %>


            </div>
        </div>
    </div>
</div>
</body>


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
</html>
