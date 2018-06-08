<%--suppress ALL --%>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="model.db.daos.LogementDAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="control.servlets.MyServlet" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Détails du logement</title>
    <meta name="description" content="GARO is a real-estate template">
    <meta name="author" content="Kimarotec">
    <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../../assets_client/css/normalize.css">
    <link rel="stylesheet" href="../../assets_client/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets_client/css/fontello.css">
    <link href="../../assets_client/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../../assets_client/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
    <link href="../../assets_client/css/animate.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../../assets/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../vendor/bootstrap_client/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets_client/css/icheck.min_all.css">
    <link rel="stylesheet" href="../../assets_client/css/price-range.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.carousel.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.theme.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.transitions.css">
    <link rel="stylesheet" href="../../assets_client/css/lightslider.min.css">
    <link rel="stylesheet" href="../../assets_client/css/style.css">
    <link rel="stylesheet" href="../../assets_client/css/responsive.css">
</head>
<body>


<!-- Body content -->

<div id="include_header"></div>
<%Logement logement = (Logement) new LogementDAO().getById(Integer.parseInt(request.getParameter("id")));%>
<%
    if (logement == null) {
        //Redirect to error page
    }
%>
<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title"><%out.print(logement.getTitre());%></h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<!-- property area -->
<div class="content-area single-property" style="background-color: #FCFCFC;">&nbsp;
    <div class="container">

        <div class="clearfix padding-top-40">

            <div class="col-md-8 single-property-content prp-style-2">
                <div class="">
                    <div class="row">
                        <div class="light-slide-item">
                            <div class="clearfix">
                                <div class="favorite-and-print">
                                    <a class="add-to-fav" href="#login-modal" data-toggle="modal">
                                        <i class="fa fa-star-o"></i>
                                    </a>
                                    <a class="printer-icon " href="javascript:window.print()">
                                        <i class="fa fa-print"></i>
                                    </a>
                                </div>

                                <ul id="image-gallery" class="gallery list-unstyled cS-hidden">
                                    <li data-thumb="assets/img/property-1/property1.jpg">
                                        <img src="../../assets_client/img/property-1/property1.jpg"/>
                                    </li>
                                    <li data-thumb="../../assets_client/img/property-1/property2.jpg">
                                        <img src="../../assets_client/img/property-1/property3.jpg"/>
                                    </li>
                                    <li data-thumb="assets/img/property-1/property3.jpg">
                                        <img src="../../assets_client/img/property-1/property3.jpg"/>
                                    </li>
                                    <li data-thumb="assets/img/property-1/property4.jpg">
                                        <img src="../../assets_client/img/property-1/property4.jpg"/>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="single-property-wrapper">

                        <div class="section">
                            <h4 class="s-property-title">Description</h4>
                            <div class="s-property-content">
                                <p>
                                    <%out.print(logement.getDescription());%>
                                </p>
                            </div>
                        </div>
                        <div class="section">
                            <h4 class="s-property-title">Adresse</h4>
                            <div class="s-property-content">
                                <p>
                                    <%out.print(logement.getAdresse());%>
                                    <br>Région: <%out.print(logement.getLocalite().getNom());%>
                                </p>
                            </div>
                        </div>
                        <!-- End description area  -->

                        <%--<div class="section additional-details">--%>

                        <%--<h4 class="s-property-title">Plus de détails</h4>--%>

                        <%--<ul class="additional-details-list clearfix">--%>
                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">Waterfront</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">Yes</span>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">Built In</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">2003</span>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">Parking</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">2 Or More Spaces,Covered Parking,Valet Parking</span>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">Waterfront</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">Yes</span>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">View</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">Intracoastal View,Direct ew</span>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                        <%--<span class="col-xs-6 col-sm-4 col-md-4 add-d-title">Waterfront Description:</span>--%>
                        <%--<span class="col-xs-6 col-sm-8 col-md-8 add-d-entry">Intracoastal Front,Ocean Access</span>--%>
                        <%--</li>--%>

                        <%--</ul>--%>
                        <%--</div>--%>
                        <!-- End additional-details area  -->

                        <div class="section property-features">

                            <h4 class="s-property-title">Détails</h4>
                            <ul>
                                <li>Avec jardin: <%
                                    if (logement.isAvecJardin()) out.print("Oui");
                                    else out.print("Non");
                                %></li>
                                <li>Avec garage: <%
                                    if (logement.isAvecGarage()) out.print("Oui");
                                    else out.print("Non");
                                %></li>
                                <li>Avec sous-sol: <%
                                    if (logement.isAvecSousSol()) out.print("Oui");
                                    else out.print("Non");
                                %></li>
                                <li>Meublé: <%
                                    if (logement.isMeubles()) out.print("Oui");
                                    else out.print("Non");
                                %></li>
                                <li>Etages: <%out.print(logement.getEtage());%></li>
                            </ul>

                        </div>
                        <!-- End features area  -->

                        <%--<div class="section property-video">--%>
                        <%--<h4 class="s-property-title">Property Video</h4>--%>
                        <%--<div class="video-thumb">--%>
                        <%--<a class="video-popup" href="yout" title="Virtual Tour">--%>
                        <%--<img src="../../assets_client/img/property-video.jpg" class="img-responsive wp-post-image" alt="Exterior">--%>
                        <%--</a>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <!-- End video area  -->

                    </div>
                </div>

                <div class="similar-post-section padding-top-40">
                    <div id="prop-smlr-slide_0">
                        <%
                            LinkedList<Logement> similar = new LogementDAO().getSimilar(logement);
                            for (Logement logement1 : similar) {
                                out.print("<div class=\"box-two proerty-item\">\n" +
                                        "                            <div class=\"item-thumb\">\n" +
                                        "                                <a href=\"/home?what=logement&id=" + logement1.getId() + "\" ><img src=\"../../assets_client/img/similar/property-1.jpg\"></a>\n" +
                                        "                            </div>\n" +
                                        "                            <div class=\"item-entry overflow\">\n" +
                                        "                                <h5><a href=\"property-1.html\"> " + logement1.getTitre() + " </a></h5>\n" +
                                        "                                <div class=\"dot-hr\"></div>\n" +
                                        "                                <span class=\"pull-left\"><b> Superficie :</b> " + logement.getSuperficie() + " </span>\n" +
                                        "                                <span class=\"proerty-price pull-right\"> " + logement.getPrix() + " M DA</span>\n" +
                                        "                            </div>\n" +
                                        "                        </div>");
                            }
                        %>

                    </div>
                </div>
            </div>

            <div class="col-md-4 p0">
                <aside class="sidebar sidebar-property blog-asside-right property-style2">
                    <div class="dealer-widget">
                        <div class="dealer-content">
                            <div class="inner-wrapper">
                                <div class="single-property-header">
                                    <h1 class="property-title"><%out.print(logement.getTitre());%></h1>
                                    <span class="property-price"><%out.print(logement.getPrix());%> M DA</span>
                                </div>

                                <div class="property-meta entry-meta clearfix ">

                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info icon-area">
                                                    <img src="../../assets_client/img/icon/room-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Superficie</span>
                                                    <span class="property-info-value"><%out.print(logement.getSuperficie());%><b
                                                            class="property-info-unit">m2</b></span>
                                                </span>
                                    </div>

                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info-icon icon-bed">
                                                    <img src="../../assets_client/img/icon/bed-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Pièces</span>
                                                    <span class="property-info-value"><%out.print(logement.getNbrPieces());%></span>
                                                </span>
                                    </div>

                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info-icon icon-bath">
                                                    <img src="../../assets_client/img/icon/cars-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Garage</span>
                                                    <span class="property-info-value"><%
                                                        if (logement.isAvecGarage()) out.print("Avec");
                                                        else out.print("Sans");
                                                    %></span>
                                                </span>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info-icon icon-bath">
                                                    <img src="../../assets_client/img/icon/os-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Jardin</span>
                                                    <span class="property-info-value"><%
                                                        if (logement.isAvecJardin()) out.print("Avec");
                                                        else out.print("Sans");
                                                    %></span>
                                                </span>
                                    </div>

                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info-icon icon-garage">
                                                    <img src="../../assets_client/img/icon/room-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Etages</span>
                                                    <span class="property-info-value"><%out.print(logement.getEtage());%></span>
                                                </span>
                                    </div>

                                    <div class="col-xs-4 col-sm-4 col-md-4 p-b-15">
                                                <span class="property-info-icon icon-garage">
                                                    <img src="../../assets_client/img/icon/shawer-orange.png">
                                                </span>
                                        <span class="property-info-entry">
                                                    <span class="property-info-label">Salles de bain</span>
                                                    <span class="property-info-value"><%out.print(logement.getNbrSdb());%></span>
                                                </span>
                                    </div>


                                </div>
                                <div class="dealer-section-space">
                                    <span>Informations</span>
                                </div>


                                <div class="clear">
                                    <ul class="dealer-contacts">
                                        <li>
                                            <i class="pe-7s-map-marker strong"> </i> <%out.print(logement.getAdresse());%>
                                        </li>
                                        <li>
                                            <i class="pe-7s-mail strong"> </i> <%out.print(new ContactInfosDAO().getEmailSociete());%>
                                        </li>
                                        <li>
                                            <i class="pe-7s-call strong"> </i> <%out.print(new ContactInfosDAO().getTelSociete());%>
                                        </li>
                                    </ul>
                                    <%
                                        String href = "";
                                        boolean isLoggedin = !(request.getSession().getAttribute(MyServlet.LOGGED_IN_USER) == null);
                                        if (isLoggedin) {
                                            href = "<a href=\"/ProgrammerVisiteClient?logementId=" + logement.getId() + "&region=" + logement.getLocalite().getId() + "&clientId=" + request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID) + "\">ici</a>";
                                        } else {
                                            href = "data-toggle=\"modal\" data-target=\"#loginRequiredModal\">ici";
                                        }
                                    %>
                                    <p>Vous pouvez programmer une visite de ce logement en nous contactant ou en
                                        cliquant <a <%out.print(href);%> </a> </p>
                                </div>

                            </div>
                        </div>
                    </div>
                </aside>
            </div>

        </div>

    </div>
</div>

<!-- Footer area-->
<div id="include_footer"></div>


<%--Modal login required--%>
<div id="loginRequiredModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Veuillez vous connecter</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <h5>Vous devez être connectés pour effectuer cette action</h5>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" onclick="window.open('/loginsignup')">
                    Connexion/Inscription
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>
<script src="../../assets_client/js/vendor/modernizr-2.6.2.min.js"></script>
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
<script type="text/javascript" src="../../assets_client/js/lightslider.min.js"></script>
<script src="../../assets_client/js/main.js"></script>

<script>
    $(document).ready(function () {

        $('#image-gallery').lightSlider({
            gallery: true,
            item: 1,
            thumbItem: 9,
            slideMargin: 0,
            speed: 500,
            auto: true,
            loop: true,
            onSliderLoad: function () {
                $('#image-gallery').removeClass('cS-hidden');
            }
        });
    });
</script>
<script>
    $(function () {
        $("#include_header").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#include_footer").load("../../jsp/client/footer.jsp");
    });
</script>
</body>
</html>