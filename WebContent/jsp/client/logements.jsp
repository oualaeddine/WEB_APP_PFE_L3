<%@ page import="control.system.managers.AuthManager" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.LogementDAO" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.db.ContactInfosDAO" %>
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
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Nos logements</title>
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
        <div class="col-md-9  pr0 padding-top-40 properties-page">
            <div class="col-md-12 clear">
                <div class="col-xs-2 layout-switcher text-center">
                    <a class="layout-list" href="javascript:void(0);"> <i class="fa fa-th-list"></i> </a>
                    <a class="layout-grid active" href="javascript:void(0);"> <i class="fa fa-th"></i> </a>
                </div>
                <!--/ .layout-switcher-->
            </div>
            <div class="col-md-12 clear">
                <div id="list-type" class="proerty-th">
                <%
                    LinkedList<Logement> logements = new LogementDAO().getAll();
                    for (Logement logement : logements) {
                        String button = "";
                        if (isLoggedIn) {
                            int client = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
                            button = new LogementDAO().isInWishList(client, logement.getId()) ? "<table><td><span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Retirer de la liste de souhaits</button></span></td><td><a href=\"\">Afficher details</a></td></table>" : "<table><td><span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Ajouter à la liste de souhaits</button></span></td><td><a href=\"\">Afficher details</a></td></table>";
                        } else {
                            button = "<table><td><span class=\"pull-right\"><button class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#loginRequiredModal\">Ajouter à la liste de souhaits</button></span></td><td><a href=\"\">Afficher details</a></td></table>";
                        }
                        out.print("<div class=\"col-sm-6 col-md-4 p0\">\n" +
                                "                            <div class=\"box-two proerty-item\">\n" +
                                "                                <div class=\"item-thumb\">\n" +
                                "                                    <a href=\"/home?what=logement&id=" + logement.getId() + "\"><img src=\"../../assets_client/img/demo/property-1.jpg\"></a>\n" +
                                "                                </div>\n" +
                                "\n" +
                                "                                <div class=\"item-entry overflow\">\n" +
                                "                                    <h5><a href=\"property-1.html\"> " + logement.getTitre() + " </a></h5>\n" +
                                "                                    <div class=\"dot-hr\"></div>\n" +
                                "                                    <span class=\"pull-left\"><b> Superficie :</b> " + logement.getSuperficie() + "</span>\n" +
                                "                                    <span class=\"proerty-price pull-right\"> " + logement.getPrix() + "m DA</span>\n" +
                                "                                    <p style=\"display: none;\">" + logement.getDescription() + "</p>\n" +
                                "                                    <div class=\"property-icon\">\n" +
                                "                                        <img src=\"../../assets_client/img/icon/bed.png\">" + logement.getNbrPieces() + "|\n" +
                                "                                        <img src=\"../../assets_client/img/icon/shawer.png\">" + logement.getNbrSdb() + "|\n" +
                                "                                        <img src=\"../../assets_client/img/icon/cars.png\">(1)\n" +
                                "                                    </div>\n" + button +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>");
                    }
                %>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer_include"></div>
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
                <h5>Vous devez être connectés pour acheter ou visiter ce logement</h5>

            </div>
            <div class="modal-footer">
                <a href="/loginsignup">
                    <button class="btn btn-primary" type="button">Connexion/Inscription</button>
                </a>
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
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
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });

    function getLogementId(selectedLogementId) {
        // document.getElementById("logementId").value = selectedLogementId;
        var dataToBeSent = {
            clientId: <%out.print(request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID));%>,
            logementId: selectedLogementId
        };

        $.ajax({
            type: 'POST',
            url: '/api/logementApi?action=addToWishList',
            data: dataToBeSent,
            success: function (result) {
                location.reload();
            },
            error: function (result) {
                location.reload();
                alert("Impossible d'ajouter/retirer le logement de votre liste de souhaits\nVeuillez reessayer");
            }

        });
    }
</script>
<script type="text/javascript">
    <%--$(function(){--%>
    <%--function getData() {--%>
    <%--var dataToBeSent  = {--%>
    <%--clientId : <%out.print(request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID));%>, //--%>
    <%--logementId: $("#logementId").val()--%>
    <%--}; // you can change parameter name--%>

    <%--$.ajax({--%>
    <%--url : '/api/logementApi?', // Your Servlet mapping or JSP(not suggested)--%>
    <%--data :dataToBeSent,--%>
    <%--type : 'POST',--%>
    <%--dataType : 'html', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.--%>
    <%--success : function(response) {--%>
    <%--$('#outputDiv').html(response); // create an empty div in your page with some id--%>
    <%--},--%>
    <%--error : function(request, textStatus, errorThrown) {--%>
    <%--alert(errorThrown);--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--});--%>
</script>
</html>
