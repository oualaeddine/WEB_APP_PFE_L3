<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="model.db.daos.LogementDAO" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Liste de souhaits</title>
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


<div id="include_header"></div>


<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">Ma liste de souhaits</h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<!-- property area -->
<div class="properties-area recent-property" style="background-color: #FFF;">
    <div class="container">
        <div class="row">

            <div class="col-md-9  pr0 padding-top-40 properties-page">
                <div class="col-md-12 clear">
                    <%--<div class="col-xs-10 page-subheader sorting pl0">--%>
                    <%--<ul class="sort-by-list">--%>
                    <%--<li class="active">--%>
                    <%--<a href="javascript:void(0);" class="order_by_date" data-orderby="property_date" data-order="ASC">--%>
                    <%--Property Date <i class="fa fa-sort-amount-asc"></i>--%>
                    <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="">--%>
                    <%--<a href="javascript:void(0);" class="order_by_price" data-orderby="property_price" data-order="DESC">--%>
                    <%--Property Price <i class="fa fa-sort-numeric-desc"></i>--%>
                    <%--</a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--<!--/ .sort-by-list-->--%>

                    <%--<div class="items-per-page">--%>
                    <%--<label for="items_per_page"><b>Property per page :</b></label>--%>
                    <%--<div class="sel">--%>
                    <%--<select id="items_per_page" name="per_page">--%>
                    <%--<option value="3">3</option>--%>
                    <%--<option value="6">6</option>--%>
                    <%--<option value="9">9</option>--%>
                    <%--<option selected="selected" value="12">12</option>--%>
                    <%--<option value="15">15</option>--%>
                    <%--<option value="30">30</option>--%>
                    <%--<option value="45">45</option>--%>
                    <%--<option value="60">60</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--<!--/ .sel-->--%>
                    <%--</div>--%>
                    <%--<!--/ .items-per-page-->--%>
                    <%--</div>--%>

                    <div class="col-xs-2 layout-switcher">
                        <a class="layout-list" href="javascript:void(0);"> <i class="fa fa-th-list"></i> </a>
                        <a class="layout-grid active" href="javascript:void(0);"> <i class="fa fa-th"></i> </a>
                    </div>
                    <!--/ .layout-switcher-->
                </div>

                <div class="col-md-12 clear">
                    <div id="list-type" class="proerty-th">
                        <%
                            int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
                            LinkedList<Logement> logements = new LogementDAO().getListeDeSouhaitsForClient(userId);
                            for (Logement logement : logements) {
                                String button = new LogementDAO().isInWishList(userId, logement.getId()) ? "<span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Retirer de la liste de souhaits</button></span>" : "<span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Ajouter à la liste de souhaits</button></span>";

                                out.print("<div class=\"col-sm-6 col-md-4 p0\">\n" +
                                        "                            <div class=\"box-two proerty-item\">\n" +
                                        "                                <div class=\"item-thumb\">\n" +
                                        "                                    <a href=\"property-1.html\"><img src=\"assets/img/demo/property-1.jpg\"></a>\n" +
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
                                        "                                    </div>\n" + "<span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Retirer de la liste de souhaits</button></span>" +
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
</div>

<!-- Footer area-->
<div id="footer_include"></div>
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
        $("#include_header").load("../../jsp/client/entete.jsp");
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
</body>

</html>