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
<div id="preloader">
    <div id="status">&nbsp;</div>
</div>
<!-- Body content -->


<div class="header-connect">
    <div class="container">
        <div class="row">
            <div class="col-md-5 col-sm-8  col-xs-12">
                <div class="header-half header-call">
                    <p>
                        <span><i class="pe-7s-call"></i> +213 696 689 498</span>
                        <span><i class="pe-7s-mail"></i> hchimmobilier@gmail.com</span>
                    </p>
                </div>
            </div>
            <div class="col-md-2 col-md-offset-5  col-sm-3 col-sm-offset-1  col-xs-12">
                <div class="header-half header-social">
                    <ul class="list-inline">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-vine"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--End top header -->
<%
    if (!isLoggedIn)
        out.print("<nav class=\"navbar navbar-default \">" +
                " <div class=\"container\">"
                + "     <!-- Brand and toggle get grouped for better mobile display -->"
                + "     <div class=\"navbar-header\">"
                + "         <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navigation\">"
                + "             <span class=\"sr-only\">Toggle navigation</span>"
                + "             <span class=\"icon-bar\"></span>"
                + "             <span class=\"icon-bar\"></span>"
                + "             <span class=\"icon-bar\"></span>"
                + "         </button>"
                + "         <a class=\"navbar-brand\" href=\"/home\"><img src=\"../../assets_client/img/logo.png\" alt=\"\"></a>"
                + "     </div>" +
                "     <!-- Collect the nav links, forms, and other content for toggling -->" +
                "     <div class=\"collapse navbar-collapse yamm\" id=\"navigation\">" +
                "         <div class=\"button navbar-right\">"
                + "             <button class=\"navbar-btn nav-button wow fadeInRight\" onclick=\" window.open('submit-property.html')\""
                + "                     data-wow-delay=\"0.5s\">Visitez un Logement"
                + "             </button>"
                + "             <button class=\"navbar-btn nav-button wow bounceInRight login\" onclick=\" window.open('/loginsignup')\""
                + "                     data-wow-delay=\"0.4s\">Login"
                + "             </button>"
                + ""
                + "         </div>"
                + "         <ul class=\"main-nav nav navbar-nav navbar-right\">"
                + "             <li class=\"wow fadeInDown \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"active\">Accueil</a>"
                + "             </li>"
                + ""
                + "             <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\"><a class=\"\" href=\"properties.html\">Nos Logements</a>"
                + "             </li>"
                + "             <!-- <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\"><a class=\"\" href=\"property.html\">A propos de <strong>HCH</strong></a></li> -->"
                + ""
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">A propos de <strong>HCH</strong> <b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"index-2.html\">Qui somme nous?</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-4.html\">Nos Services</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-4.html\">Nos Partenaires</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-3.html\">Regles d'utilisation</a>"
                + "                     </li>"
                + ""
                + "                 </ul>"
                + "             </li>"
                + "             <li class=\"wow fadeInDown\" data-wow-delay=\"0.4s\"><a href=\"contact.html\">Contactez nous</a></li>"
                + "         </ul>"
                + "     </div>"
                + "     <!-- /.navbar-collapse -->"
                + " </div>"
                + " <!-- /.container-fluid -->" +
                "</nav>" +
                "<!-- End of nav bar -->");
    else {
        Client client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
        out.print("<nav class=\"navbar navbar-default \">" +
                " <div class=\"container\">"
                + "     <!-- Brand and toggle get grouped for better mobile display -->"
                + "     <div class=\"navbar-header\">"
                + "         <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navigation\">"
                + "             <span class=\"sr-only\">Toggle navigation</span>"
                + "             <span class=\"icon-bar\"></span>"
                + "             <span class=\"icon-bar\"></span>"
                + "             <span class=\"icon-bar\"></span>"
                + "         </button>"
                + "         <a class=\"navbar-brand\" href=\"/home\"><img src=\"../../assets_client/img/logo.png\" alt=\"\"></a>"
                + "     </div>" +
                "     <!-- Collect the nav links, forms, and other content for toggling -->" +
                "     <div class=\"collapse navbar-collapse yamm\" id=\"navigation\">" +
                "         <div class=\"button navbar-right\">"
                + "             <button class=\"navbar-btn nav-button wow fadeInRight\" onclick=\" window.open('submit-property.html')"
                + "                     data-wow-delay=\"0.5s\">Visitez un Logement"
                + "             </button>"
                + "         </div>"
                + "         <ul class=\"main-nav nav navbar-nav navbar-right\">"
                + "             <li class=\"wow fadeInDown \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"active\">Accueil</a>"
                + "             </li>"
                + ""
                + "             <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\">" +
                "                   <a class=\"\" href=\"properties.html\">Nos Logements</a>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">A propos de <strong>HCH</strong> <b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"index-2.html\">Qui somme nous?</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-4.html\">Nos Services</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-4.html\">Nos Partenaires</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-3.html\">Regles d'utilisation</a>"
                + "                     </li>" +
                "                       <li>" +
                "                           <a href=\"contact.html\">Contactez nous</a>" +
                "                       </li>"
                + ""
                + "                 </ul>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">Mon espace<b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"/ClientServlet?what=myVisits\">Mes visites</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/ClientServlet?what=mesNotifs\">Mes notifications</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/ClientServlet?what=mesVentes\">Mes ventes en cours</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/ClientServlet?what=mesLogements\">Mes logements visités</a>"
                + "                     </li>"
                + "                 </ul>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\"><i class=\"fa fa-user\"></i>     " + client.getUsername() + "<b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a class=\"dropdown-toggle\" href=\"\" >" +
                "                              Modifier mes informations" +
                "                           </a>"
                + "                     </li>"
                + "                     <li>" +
                "                           <a class\"dropdown-toggle\" href=\"/logout\">" +
                "                               Déconnexion" +
                "                           </a>" +
                "                       </li>"
                + "                 </ul>"
                + "             </li>"

                + "         </ul>"
                + "     </div>"
                + "     <!-- /.navbar-collapse -->"
                + " </div>"
                + " <!-- /.container-fluid -->" +
                "</nav>" +
                "<!-- End of nav bar -->");

    }


%>

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

</html>
