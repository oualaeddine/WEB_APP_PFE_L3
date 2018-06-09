<%@ page import="control.servlets.MyServlet" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="control.statistics.globales.RapportsStats" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page import="model.db.daos.EmployeDAO" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page import="model.db.daos.LogementDAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 4/3/2018
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    LogementsStats logementsStats = new LogementsStats();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Accueil</title>
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

<%--<div id="preloader">--%>
<%--<div id="status">&nbsp;</div>--%>
<%--</div>--%>
<!-- Body content -->


<div id="include_html"></div>


<div class="slider-area">
    <div class="slider">
        <div id="bg-slider" class="owl-carousel owl-theme">

            <div class="item"><img src="../../assets_client/img/slide1/slider-image-2.jpg" alt="The Last of us"></div>
            <div class="item"><img src="../../assets_client/img/slide1/slider-image-1.jpg" alt="GTA V"></div>

        </div>
    </div>
    <div class="container slider-content">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
                <h2>Trouver la maison de vos rêves devient si facile !</h2>
                <h3 style="color:#1c2529;">Recherchez , Visitez , Achetez !</h3>
            </div>
        </div>
    </div>
</div>

<div class="home-lager-shearch" style="background-color: rgb(252, 252, 252); padding-top: 25px; margin-top: -125px;">
    <div class="container">
        <div class="col-md-12 large-search">
            <div class="search-form wow pulse">
                <form action="#" method="get" class=" form-inline">
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <input type="text" class="form-control" placeholder="Key word">
                        </div>
                        <div class="col-md-4">
                            <select id="lunchBegins" class="selectpicker" data-live-search="true"
                                    data-live-search-style="begins" title="Select your city">


                            </select>
                        </div>
                        <div class="col-md-4">
                            <select id="basic" class="selectpicker show-tick form-control">
                                <option> -Status-</option>
                                <option>Rent</option>
                                <option>Boy</option>
                                <option>used</option>

                            </select>
                        </div>
                    </div>
                    <div class="col-md-12 ">
                        <div class="search-row">

                            <div class="col-sm-3">
                                <label for="price-range">Price range ($):</label>
                                <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600"
                                       data-slider-step="5" data-slider-value="[0,450]" id="price-range"><br/>
                                <b class="pull-left color">2000$</b>
                                <b class="pull-right color">100000$</b>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <label for="property-geo">Property geo (m2) :</label>
                                <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600"
                                       data-slider-step="5" data-slider-value="[50,450]" id="property-geo"><br/>
                                <b class="pull-left color">40m</b>
                                <b class="pull-right color">12000m</b>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <label for="price-range">Min baths :</label>
                                <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600"
                                       data-slider-step="5" data-slider-value="[250,450]" id="min-baths"><br/>
                                <b class="pull-left color">1</b>
                                <b class="pull-right color">120</b>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <label for="property-geo">Min bed :</label>
                                <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600"
                                       data-slider-step="5" data-slider-value="[250,450]" id="min-bed"><br/>
                                <b class="pull-left color">1</b>
                                <b class="pull-right color">120</b>
                            </div>
                            <!-- End of  -->

                        </div>

                        <div class="search-row">

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Fire Place(3100)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Dual Sinks(500)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Hurricane Shutters(99)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Swimming Pool(1190)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> 2 Stories(4600)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Emergency Exit(200)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Laundry Room(10073)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Jog Path(1503)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->

                            <div class="col-sm-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> 26' Ceilings(1200)
                                    </label>
                                </div>
                            </div>
                            <!-- End of  -->
                        </div>
                    </div>
                    <input type="hidden" name="action" value="search">
                    <div class="center">
                        <input type="submit" value="" class="btn btn-default btn-lg-sheach">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- property area -->
<div class="content-area recent-property" style="background-color: #FCFCFC; padding-bottom: 55px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                <!-- /.feature title -->
                <h2>Logements les plus visités</h2>
                <%--<p>Nulla quis dapibus nisl. Suspendisse ultricies commodo arcu nec pretium. Nullam sed arcu ultricies--%>
                <%--. </p>--%>
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
            <div id="list-type" class="property-th">
                <%
                    LinkedList<Logement> logements = (LinkedList<Logement>) request.getAttribute("logements");
                    Client loggedIdClient = new Client();
                    String href = "data-toggle=\"modal\" data-target=\"#loginRequiredModal\"";
                    if (isLoggedIn) {
                        loggedIdClient = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
                    }
                    for (Logement logement : logements) {
                        String button = "";
                        if (isLoggedIn) {
                            int client = Integer.parseInt(String.valueOf(request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID)));
                            button = new LogementDAO().isInWishList(client, logement.getId()) ? "<span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Retirer de la liste de souhaits</button></span>" : "<span class=\"pull-right\"><button class=\"btn btn-primary\" onclick=\"getLogementId(" + logement.getId() + ")\">Ajouter à la liste de souhaits</button></span>";
                            href = "href=\"/ProgrammerVisiteClient?logementId=" + logement.getId() + "&region=" + logement.getLocalite().getId() + "&clientId=" + loggedIdClient.getId() + "\"";
                        } else {
                            href = "data-toggle=\"modal\" data-target=\"#loginRequiredModal\"";
                            button = "<span class=\"pull-right\"><button class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#loginRequiredModal\">Ajouter à la liste de souhaits</button></span>";
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
                                "                                    <span class=\"proerty-price pull-right\"> " + logement.getPrix() / 1000000 + "m DA</span>\n" +
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


                <div class="col-sm-6 col-md-3 p0">
                    <div class="box-tree more-proerty text-center">
                        <div class="item-tree-icon">
                            <i class="fa fa-th"></i>
                        </div>
                        <div class="more-entry overflow">
                            <h5><a href="/DashboardServlet?what=logements">IMPOSSIBLE DE SE DECIDER ? </a></h5>
                            <h5 class="tree-sub-ttl">Voir tous les logements</h5>
                            <a href="/DashboardServlet?what=logements">
                                <button class="btn border-btn more-black" value="All properties">TOUS LES LOGEMENTS
                                </button>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!--Welcome area -->
<div class="Welcome-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12 Welcome-entry  col-sm-12">
                <div class="col-md-5 col-md-offset-2 col-sm-6 col-xs-12">
                    <div class="welcome_text wow fadeInLeft" data-wow-delay="0.3s" data-wow-offset="100">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                                <!-- /.feature title -->
                                <h2><%out.print(new ContactInfosDAO().getNomSociete());%></h2>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-sm-6 col-xs-12">
                    <div class="welcome_services wow fadeInRight" data-wow-delay="0.3s" data-wow-offset="100">
                        <div class="row">
                            <div class="col-xs-6 m-padding">
                                <div class="welcome-estate">
                                    <div class="welcome-icon">
                                        <i class="pe-7s-home pe-4x"></i>
                                    </div>
                                    <h3>Logements</h3>
                                </div>
                            </div>
                            <div class="col-xs-6 m-padding">
                                <div class="welcome-estate">
                                    <div class="welcome-icon">
                                        <i class="pe-7s-users pe-4x"></i>
                                    </div>
                                    <h3>Contact</h3>
                                </div>
                            </div>


                            <div class="col-xs-12 text-center">
                                <i class="welcome-circle"></i>
                            </div>

                            <div class="col-xs-6 m-padding">
                                <div class="welcome-estate">
                                    <div class="welcome-icon">
                                        <i class="pe-7s-notebook pe-4x"></i>
                                    </div>
                                    <h3>Facile à utiliser</h3>
                                </div>
                            </div>
                            <div class="col-xs-6 m-padding">
                                <div class="welcome-estate">
                                    <div class="welcome-icon">
                                        <i class="pe-7s-help2 pe-4x"></i>
                                    </div>
                                    <h3>Besoin d'aide </h3>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Count area -->
<div class="count-area">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                <!-- /.feature title -->
                <h2>Vous pouvez nous faire confiance </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-xs-12 percent-blocks m-main" data-waypoint-scroll="true">
                <div class="row">
                    <div class="col-sm-3 col-xs-6">
                        <div class="count-item">
                            <div class="count-item-circle">
                                <span class="pe-7s-users"></span>
                            </div>
                            <div class="chart" data-percent="5000">
                                <h2 class="percent" id="counter">0</h2>
                                <h5>Clients </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <div class="count-item">
                            <div class="count-item-circle">
                                <span class="pe-7s-home"></span>
                            </div>
                            <div class="chart" data-percent="12000">
                                <h2 class="percent" id="counter1">0</h2>
                                <h5>Logements à vendre</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <div class="count-item">
                            <div class="count-item-circle">
                                <span class="pe-7s-flag"></span>
                            </div>
                            <div class="chart" data-percent="120">
                                <h2 class="percent" id="counter2">0</h2>
                                <h5>Localités </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <div class="count-item">
                            <div class="count-item-circle">
                                <span class="pe-7s-graph2"></span>
                            </div>
                            <div class="" data-percent="5000">
                                <h2 class="percent" id="counter3">5000</h2>
                                <h5>EMPLOYES</h5>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- boy-sale area -->
<div class="boy-sale-area">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <p class="asks-call">DES QUESTIONS ? APPELLEZ-NOUS : <span
                        class="strong"> <%out.print(new ContactInfosDAO().getTelSociete());%></span></p>
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


<div id="programmerVisiteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Programmer une visite</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form method="post" action="/AjoutServlet?ajouter=visite" id="newVisitForm">


                    <input id="logementChoisi" name="logementChoisi" type="hidden">
                    <div class="form-group">
                        <label for="userTypeInput">Choisissez une date</label>
                        <select class="custom-select" name="userTypeInput" id="userTypeInput">
                            <option value="responsableVentes">Choisir une date</option>
                        </select>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="submit">Valider</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>

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
                <a href="/loginsignup">
                    <button class="btn btn-primary" type="button">Connexion/Inscription</button>
                </a>
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function getLogementId(idTaaLogement) {
        document.getElementById("logementChoisi").value = idTaaLogement;
    }

</script>


<style>
    .navbar-login {
        width: 305px;
        padding: 10px;
        padding-bottom: 0px;
    }

    .navbar-login-session {
        padding: 10px;
        padding-bottom: 0px;
        padding-top: 0px;
    }

    .icon-size {
        font-size: 87px;
    }
</style>


<
<script>

    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });

    $(window).load(function () { // makes sure the whole site is loaded
        $('#status').fadeOut(); // will first fade out the loading animation
        $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
        $('body').delay(350).css({'overflow': 'visible'});
    });
    $(document).ready(function () {


        $('input').iCheck({
            checkboxClass: 'icheckbox_square-yellow',
            radioClass: 'iradio_square-yellow',
            increaseArea: '20%' // optional
        });


        $('.layout-grid').on('click', function () {
            $('.layout-grid').addClass('active');
            $('.layout-list').removeClass('active');

            $('#list-type').removeClass('proerty-th-list');
            $('#list-type').addClass('proerty-th');

        });

        $('.layout-list').on('click', function () {
            $('.layout-grid').removeClass('active');
            $('.layout-list').addClass('active');

            $('#list-type').addClass('proerty-th-list');
            $('#list-type').removeClass('proerty-th');

        });

    });
    $(document).ready(function () {
        $("#bg-slider").owlCarousel({
            navigation: false, // Show next and prev buttons
            slideSpeed: 100,
            autoPlay: 5000,
            paginationSpeed: 100,
            singleItem: true,
            mouseDrag: false,
            transitionStyle: "fade"
            // "singleItem:true" is a shortcut for:
            // items : 1,
            // itemsDesktop : false,
            // itemsDesktopSmall : false,
            // itemsTablet: false,
            // itemsMobile : false
        });
        $("#prop-smlr-slide_0").owlCarousel({
            navigation: false, // Show next and prev buttons
            slideSpeed: 100,
            pagination: true,
            paginationSpeed: 100,
            items: 3

        });
        $("#testimonial-slider").owlCarousel({
            navigation: false, // Show next and prev buttons
            slideSpeed: 100,
            pagination: true,
            paginationSpeed: 100,
            items: 3
        });

        $('#price-range').slider();
        $('#property-geo').slider();
        $('#min-baths').slider();
        $('#min-bed').slider();

        var RGBChange = function () {
            $('#RGB').css('background', '#FDC600')
        };

        // Advanced search toggle
        var $SearchToggle = $('.search-form .search-toggle');
        $SearchToggle.hide();

        $('.search-form .toggle-btn').on('click', function (e) {
            e.preventDefault();
            $SearchToggle.slideToggle(300);
        });

        setTimeout(function () {
            $('#counter').text('0');
            $('#counter1').text('0');
            $('#counter2').text('0');
            $('#counter3').text('0');
            setInterval(function () {
                var curval = parseInt($('#counter').text());
                var curval1 = parseInt($('#counter1').text().replace(' ', ''));
                var curval2 = parseInt($('#counter2').text());
                var curval3 = parseInt($('#counter3').text());

                $('#counter').text(<%out.print(""+new RapportsStats().positifsNbr());%>);
                $('#counter1').text(<%out.print(""+new LogementsStats().logementsAVendre());%>);
                $('#counter2').text(<%out.print(""+new LocaliteDAO().countAll());%>);
                $('#counter3').text(<%out.print(""+new EmployeDAO().countAll());%>);

            }, 2);
        }, 500);

        function sdf_FTS(_number, _decimal, _separator) {
            var decimal = (typeof (_decimal) != 'undefined') ? _decimal : 2;
            var separator = (typeof (_separator) != 'undefined') ? _separator : '';
            var r = parseFloat(_number);
            var exp10 = Math.pow(10, decimal);
            r = Math.round(r * exp10) / exp10;
            rr = Number(r).toFixed(decimal).toString().split('.');
            b = rr[0].replace(/(\d{1,3}(?=(\d{3})+(?:\.\d|\b)))/g, "\$1" + separator);
            r = (rr[1] ? b + '.' + rr[1] : b);

            return r;
        }

    });

    // Initializing WOW.JS

    new WOW().init();
</script>

<%
    if (request.getParameter("error") != null) {
        int errorId = Integer.parseInt(request.getParameter("error"));
        String errorMessage = "Unidentified error";
        switch (errorId) {
            case MyServlet.ACTION_ERROR:
                errorMessage = "Action non effectuee, Veuillez reessayer";
                break;
            case MyServlet.ACTION_SUCCESS:
                errorMessage = "Action bien effectuee !";
                break;
        }
        out.print("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
%>

</body>

