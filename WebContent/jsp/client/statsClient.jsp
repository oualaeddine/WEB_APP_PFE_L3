<%@ page import="control.servlets.MyServlet" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="control.statistics.globales.ClientsStats" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="control.statistics.globales.VentesStats" %>
<%@ page import="control.statistics.globales.VisitesStats" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    LogementsStats logementsStats = new LogementsStats();
    VisitesStats visitesStats = new VisitesStats();
    VentesStats ventesStats = new VentesStats();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HCH Immobilier | Statistiques</title>
    <meta name="description" content="GARO is a real-estate template">
    <meta name="author" content="Kimarotec">
    <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../statistics/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../statistics/st/bower_components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../statistics/st/bower_components/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="../../statistics/st/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="../../statistics/st/dist/css/skins/_all-skins.min.css">

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
<div class="container">
    <div id="include_html"></div>

    <div class="home-lager-shearch" <%--style="background-color: rgb(252, 252, 252); padding-top: 25px; margin-top: -125px;"--%>>
        <div class="container">
            <div class="box-body">
                <div class="row">
                    <div class="col-md-8">
                        <p class="text-center">
                            <strong>Logements vendus
                                de
                                : 1
                                Jan, <%out.print(Calendar.getInstance().get(Calendar.YEAR));%> - 31 Decembre,
                                <%out.print(Calendar.getInstance().get(Calendar.YEAR));%></strong>
                        </p>

                        <div class="chart">
                            <!-- Sales Chart Canvas -->
                            <canvas id="salesChart" style="height: 300px;"></canvas>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <p class="text-center">
                            <strong>Nos statistiques</strong>
                        </p>

                        <!-- /.progress-group -->
                        <div class="progress-group">
                            <span class="progress-text">Nombre de logements vendus:</span>
                            <span class="progress-number"><b><%out.print(logementsStats.logementsVendusNbr());%></b></span>
                            <div class="progress sm">
                                <div class="progress-bar progress-bar-green"
                                     style="width:<%out.print(logementsStats.logementsVendusPercentage());%>%"></div>
                            </div>
                        </div>
                        <div class="progress-group">
                            <span class="progress-text">Nombre de visites ce mois</span>
                            <span class="progress-number"><b><%out.print(visitesStats.allVisitesByMonth(Month.of(Calendar.getInstance().get(Calendar.MONTH))));%></b></span>

                            <div class="progress sm">
                                <div class="progress-bar progress-bar-success"
                                     style="width: 100%"></div>
                            </div>
                        </div>
                        <!-- /.progress-group -->
                        <div class="progress-group">
                            <span class="progress-text">Logements vendus ce mois</span>
                            <span class="progress-number"><b><%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.of(Calendar.getInstance().get(Calendar.MONTH))));%></b></span>

                            <div class="progress sm">
                                <div class="progress-bar progress-bar-primary"
                                     style="width: 100%"></div>
                            </div>
                        </div>

                        <!-- /.progress-group -->
                        <div class="progress-group">
                            <span class="progress-text">Nombre d'acheteurs</span>
                            <span class="progress-number"><b><%out.print(ventesStats.acheteursNbr());%></b>/<%out.print(new ClientsStats().clientsNbr());%></span>

                            <div class="progress sm">
                                <div class="progress-bar progress-bar-yellow"
                                     style="width:<%out.print(ventesStats.acheteursPercentage());%>%"></div>
                            </div>
                        </div>
                        <!-- /.progress-group -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <p class="text-center">
                            <strong>Logements par régions</strong>
                        </p>

                        <div class="chart">
                            <!-- Sales Chart Canvas -->
                            <canvas id="logementsPerRegion" style="height: 300px;"></canvas>
                        </div>
                    </div>
                    <!-- /.col -->

                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
        </div>
    </div>
</div>
<div class="container">
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
            <div class="row">
                <div class="property-th">
                    <%
                        LinkedList
                                <
                                        Logement
                                        >
                                logements
                                =
                                logementsStats
                                        .
                                                mostVisitedLogements
                                                        (
                                                        );
                        Client
                                loggedIdClient
                                =
                                new
                                        Client
                                        (
                                        );
                        String
                                href
                                =
                                "data-toggle=\"modal\" data-target=\"#loginRequiredModal\"";
                        if
                                (
                                isLoggedIn
                                ) {
                            loggedIdClient
                                    =
                                    (
                                            Client
                                            )
                                            request
                                                    .
                                                            getSession
                                                                    (
                                                                    )
                                                    .
                                                            getAttribute
                                                                    (
                                                                            MyServlet
                                                                                    .
                                                                                    LOGGED_IN_USER
                                                                    )
                            ;
                        }
                        for
                                (
                                Logement
                                        logement
                                :
                                logements
                                ) {
                            if
                                    (
                                    isLoggedIn
                                    ) {
                                href
                                        =
                                        "href=\"/ProgrammerVisiteClient?logementId="
                                                +
                                                logement
                                                        .
                                                                getId
                                                                        (
                                                                        )
                                                +
                                                "&region="
                                                +
                                                logement
                                                        .
                                                                getLocalite
                                                                        (
                                                                        )
                                                        .
                                                                getId
                                                                        (
                                                                        )
                                                +
                                                "&clientId="
                                                +
                                                loggedIdClient
                                                        .
                                                                getId
                                                                        (
                                                                        )
                                                +
                                                "\""
                                ;
                            }
                            out
                                    .
                                            print
                                                    (
                                                            "<div class=\"col-sm-6 col-md-3 p0\">\n"
                                                                    +
                                                                    "                    <div class=\"box-two proerty-item\">\n"
                                                                    +
                                                                    "                        <div class=\"item-thumb\">\n"
                                                                    +
                                                                    "                            <a "
                                                                    +
                                                                    href
                                                                    +
                                                                    " ><img src=\"../../assets_client/img/demo/property-1.jpg\"></a>\n"
                                                                    +
                                                                    "                        </div>\n"
                                                                    +
                                                                    "                        <div class=\"item-entry overflow\">\n"
                                                                    +
                                                                    "                            <h5><a "
                                                                    +
                                                                    href
                                                                    +
                                                                    ">"
                                                                    +
                                                                    logement
                                                                            .
                                                                                    getTitre
                                                                                            (
                                                                                            )
                                                                    +
                                                                    " </a></h5>\n"
                                                                    +
                                                                    "                            <div class=\"dot-hr\"></div>\n"
                                                                    +
                                                                    "                            <span class=\"pull-left\"><b>Area :</b> "
                                                                    +
                                                                    logement
                                                                            .
                                                                                    getSuperficie
                                                                                            (
                                                                                            )
                                                                    +
                                                                    "m2</span>\n"
                                                                    +
                                                                    "                            <span class=\"proerty-price pull-right\">"
                                                                    +
                                                                    logement
                                                                            .
                                                                                    getPrix
                                                                                            (
                                                                                            )
                                                                    +
                                                                    " DZD</span>\n"
                                                                    +
                                                                    "                        </div>\n"
                                                                    +
                                                                    "                    </div>\n"
                                                                    +
                                                                    "                </div>"
                                                    )
                            ;
                        }
                    %>


                    <div class="col-sm-6 col-md-3 p0">
                        <div class="box-tree more-proerty text-center">
                            <div class="item-tree-icon">
                                <i class="fa fa-th"></i>
                            </div>
                            <div class="more-entry overflow">
                                <h5><a href="logements.jsp">IMPOSSIBLE DE SE DECIDER ? </a></h5>
                                <h5 class="tree-sub-ttl">Voir tous les logements</h5>
                                <a href="/ClientServlet?what=logements">
                                    <button class="btn border-btn more-black" value="All properties">Tous les
                                        logements
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
                                    <h2>HCH Immobilier </h2>
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
                                        <h3>Any property</h3>
                                    </div>
                                </div>
                                <div class="col-xs-6 m-padding">
                                    <div class="welcome-estate">
                                        <div class="welcome-icon">
                                            <i class="pe-7s-users pe-4x"></i>
                                        </div>
                                        <h3>More Clients</h3>
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
                                        <h3>Easy to use</h3>
                                    </div>
                                </div>
                                <div class="col-xs-6 m-padding">
                                    <div class="welcome-estate">
                                        <div class="welcome-icon">
                                            <i class="pe-7s-help2 pe-4x"></i>
                                        </div>
                                        <h3>Any help </h3>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--TESTIMONIALS -->
    <div class="testimonial-area recent-property" style="background-color: #FCFCFC; padding-bottom: 15px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title">
                    <!-- /.feature title -->
                    <h2>Our Customers Said </h2>
                </div>
            </div>

            <div class="row">
                <div class="row testimonial">
                    <div class="col-md-12">
                        <div id="testimonial-slider">
                            <div class="item">
                                <div class="client-text">
                                    <p>Nulla quis dapibus nisl. Suspendisse llam sed arcu ultried arcu ultricies !</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face wow fadeInRight" data-wow-delay=".9s">
                                    <img src="../../assets_client/img/client-face1.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">
                                    <p>Nulla quis dapibus nisl. Suspendisse llam sed arcu ultried arcu ultricies !</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="../../assets_client/img/client-face2.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">
                                    <p>Nulla quis dapibus nisl. Suspendisse llam sed arcu ultried arcu ultricies !</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="../../assets_client/img/client-face1.png" alt="">
                                </div>
                            </div>
                            <div class="item">
                                <div class="client-text">
                                    <p>Nulla quis dapibus nisl. Suspendisse llam sed arcu ultried arcu ultricies !</p>
                                    <h4><strong>Ohidul Islam, </strong><i>Web Designer</i></h4>
                                </div>
                                <div class="client-face">
                                    <img src="../../assets_client/img/client-face2.png" alt="">
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
                    <h2>You can trust Us </h2>
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
                                    <h5>HAPPY CUSTOMER </h5>
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
                                    <h5>Properties in stock</h5>
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
                                    <h5>City registered </h5>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3 col-xs-6">
                            <div class="count-item">
                                <div class="count-item-circle">
                                    <span class="pe-7s-graph2"></span>
                                </div>
                                <div class="chart" data-percent="5000">
                                    <h2 class="percent" id="counter3">5000</h2>
                                    <h5>DEALER BRANCHES</h5>
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

                <div class="col-md-6 col-sm-10 col-sm-offset-1 col-md-offset-0 col-xs-12">
                    <div class="asks-first">
                        <div class="asks-first-circle">
                            <span class="fa fa-search"></span>
                        </div>
                        <div class="asks-first-info">
                            <h2>ARE YOU LOOKING FOR A Property?</h2>
                            <p> varius od lio eget conseq uat blandit, lorem auglue comm lodo nisl no us nibh mas
                                lsa</p>
                        </div>
                        <div class="asks-first-arrow">
                            <a href="properties.html"><span class="fa fa-angle-right"></span></a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-10 col-sm-offset-1 col-xs-12 col-md-offset-0">
                    <div class="asks-first">
                        <div class="asks-first-circle">
                            <span class="fa fa-usd"></span>
                        </div>
                        <div class="asks-first-info">
                            <h2>DO YOU WANT TO SELL A Property?</h2>
                            <p> varius od lio eget conseq uat blandit, lorem auglue comm lodo nisl no us nibh mas
                                lsa</p>
                        </div>
                        <div class="asks-first-arrow">
                            <a href="properties.html"><span class="fa fa-angle-right"></span></a>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12">
                    <p class="asks-call">QUESTIONS? CALL US : <span class="strong"> + 3-123- 424-5700</span></p>
                </div>
            </div>
        </div>
    </div>


    <!-- Footer area-->
    <div class="footer-area">

        <div class=" footer">
            <div class="container">
                <div class="row">

                    <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                        <div class="single-footer">
                            <h4>About us </h4>
                            <div class="footer-title-line"></div>

                            <img src="../../assets_client/img/footer-logo.png" alt="" class="wow pulse"
                                 data-wow-delay="1s">
                            <p>Lorem ipsum dolor cum necessitatibus su quisquam molestias. Vel unde, blanditiis.</p>
                            <ul class="footer-adress">
                                <li><i class="pe-7s-map-marker strong"> </i> 9089 your adress her</li>
                                <li><i class="pe-7s-mail strong"> </i> email@yourcompany.com</li>
                                <li><i class="pe-7s-call strong"> </i> +1 908 967 5906</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                        <div class="single-footer">
                            <h4>Quick links </h4>
                            <div class="footer-title-line"></div>
                            <ul class="footer-menu">
                                <li><a href="properties.html">Properties</a></li>
                                <li><a href="#">Services</a></li>
                                <li><a href="submit-property.html">Submit property </a></li>
                                <li><a href="contact.html">Contact us</a></li>
                                <li><a href="faq.html">fqa</a></li>
                                <li><a href="faq.html">Terms </a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                        <div class="single-footer">
                            <h4>Last News</h4>
                            <div class="footer-title-line"></div>
                            <ul class="footer-blog">
                                <li>
                                    <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
                                        <a href="single.html">
                                            <img src="../../assets_client/img/demo/small-proerty-2.jpg">
                                        </a>
                                        <span class="blg-date">12-12-2016</span>

                                    </div>
                                    <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
                                        <h6><a href="single.html">Add news functions </a></h6>
                                        <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet,
                                            nulla
                                            ...</p>
                                    </div>
                                </li>

                                <li>
                                    <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
                                        <a href="single.html">
                                            <img src="../../assets_client/img/demo/small-proerty-2.jpg">
                                        </a>
                                        <span class="blg-date">12-12-2016</span>

                                    </div>
                                    <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
                                        <h6><a href="single.html">Add news functions </a></h6>
                                        <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet,
                                            nulla
                                            ...</p>
                                    </div>
                                </li>

                                <li>
                                    <div class="col-md-3 col-sm-4 col-xs-4 blg-thumb p0">
                                        <a href="single.html">
                                            <img src="../../assets_client/img/demo/small-proerty-2.jpg">
                                        </a>
                                        <span class="blg-date">12-12-2016</span>

                                    </div>
                                    <div class="col-md-8  col-sm-8 col-xs-8  blg-entry">
                                        <h6><a href="single.html">Add news functions </a></h6>
                                        <p style="line-height: 17px; padding: 8px 2px;">Lorem ipsum dolor sit amet,
                                            nulla
                                            ...</p>
                                    </div>
                                </li>


                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                        <div class="single-footer news-letter">
                            <h4>Stay in touch</h4>
                            <div class="footer-title-line"></div>
                            <p>Lorem ipsum dolor sit amet, nulla suscipit similique quisquam molestias. Vel unde,
                                blanditiis.</p>

                            <form>
                                <div class="input-group">
                                    <input class="form-control" type="text" placeholder="E-mail ... ">
                                    <span class="input-group-btn">
                                            <button class="btn btn-primary subscribe" type="button"><i
                                                    class="pe-7s-paper-plane pe-2x"></i></button>
                                        </span>
                                </div>
                                <!-- /input-group -->
                            </form>

                            <div class="social pull-right">
                                <ul>
                                    <li><a class="wow fadeInUp animated" href="https://twitter.com/kimarotec"><i
                                            class="fa fa-twitter"></i></a></li>
                                    <li><a class="wow fadeInUp animated" href="https://www.facebook.com/kimarotec"
                                           data-wow-delay="0.2s"><i class="fa fa-facebook"></i></a></li>
                                    <li><a class="wow fadeInUp animated" href="https://plus.google.com/kimarotec"
                                           data-wow-delay="0.3s"><i class="fa fa-google-plus"></i></a></li>
                                    <li><a class="wow fadeInUp animated" href="https://instagram.com/kimarotec"
                                           data-wow-delay="0.4s"><i class="fa fa-instagram"></i></a></li>
                                    <li><a class="wow fadeInUp animated" href="https://instagram.com/kimarotec"
                                           data-wow-delay="0.6s"><i class="fa fa-dribbble"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="footer-copy text-center">
            <div class="container">
                <div class="row">
                    <div class="pull-left">
                        <span> (C) <a
                                href="http://www.KimaroTec.com">KimaroTheme</a> , All rights reserved 2016  </span>
                    </div>
                    <div class="bottom-menu pull-right">
                        <ul>
                            <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.2s">Home</a></li>
                            <li><a class="wow fadeInUp animated" href="/login" data-wow-delay="0.3s">Employé</a></li>
                            <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.4s">Faq</a></li>
                            <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.6s">Contact</a></li>
                        </ul>
                    </div>
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
<script src="../../statistics/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../../statistics/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../statistics/bower_components/fastclick/lib/fastclick.js"></script>
<script src="../../statistics/dist/js/adminlte.min.js"></script>
<script src="../../statistics/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<script src="../../statistics/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="../../statistics/bower_components/chart.js/Chart.js"></script>
<script src="../../statistics/dist/js/demo.js"></script>
</body>

<script>
    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });


    'use strict';
    /* ChartJS
 * -------
 * Here we will create a few charts using ChartJS
 */

    // -----------------------
    // - MONTHLY SALES CHART -
    // -----------------------

    // Get context with jQuery - using jQuery's .get() method.
    var salesChartCanvas = $('#salesChart').get(0).getContext('2d');
    // This will get the first returned node in the jQuery collection.

    var salesChartData = {
        labels: ['Janvier', 'Fevrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Aout', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        datasets: [
            {
                label: 'Ventes par mois',
                backgroundColor: '#605ca8',
                data: [
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JANUARY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.FEBRUARY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.MARCH));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.APRIL));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.MAY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JUNE));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.JULY));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.AUGUST));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.SEPTEMBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.OCTOBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.NOVEMBER));%>,
                    <%out.print(ventesStats.confirmedVentesNbrPerMonth(Month.DECEMBER));%>
                ]
            }
        ]
    };

    var salesChartOptions = {
        // Boolean - If we should show the scale at all
        showScale: true,
        // Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: false,
        // String - Colour of the grid lines
        scaleGridLineColor: 'rgba(0,0,0,.05)',
        // Number - Width of the grid lines
        scaleGridLineWidth: 1,
        // Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        // Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        // Boolean - Whether the line is curved between points
        bezierCurve: true,
        // Number - Tension of the bezier curve between points
        bezierCurveTension: 0.3,
        // Boolean - Whether to show a dot for each point
        pointDot: false,
        // Number - Radius of each point dot in pixels
        pointDotRadius: 4,
        // Number - Pixel width of point dot stroke
        pointDotStrokeWidth: 1,
        // Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius: 20,
        // Boolean - Whether to show a stroke for datasets
        datasetStroke: true,
        // Number - Pixel width of dataset stroke
        datasetStrokeWidth: 2,
        // Boolean - Whether to fill the dataset with a color
        datasetFill: true,
        // String - A legend template
        <%--legendTemplate: "<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<datasets.length; i++){%><li><span style=\'background-color:<%=datasets[i].lineColor%>\'></span><%=datasets[i].label%></li><%}%></ul>",--%>
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true
    };
    <%LinkedList<Localite> localites = visitesStats.top5Regions(); %>
    // Create the line chart
    var salesChart = new Chart(salesChartCanvas, {
        type: 'line',
        data: salesChartData,
        options: salesChartOptions
    });
</script>
<script>
    'use strict';
    /* ChartJS
 * -------
 * Here we will create a few charts using ChartJS
 */

    // -----------------------
    // - MONTHLY SALES CHART -
    // -----------------------

    // Get context with jQuery - using jQuery's .get() method.
    var logementChartCanvas = $('#logementsPerRegion').get(0).getContext('2d');
    // This will get the first returned node in the jQuery collection.
    var logementsChartData = {
        labels: [
            <%
                LinkedList<Localite> allRegions = new LocaliteDAO().getAll();
                for(Localite localite : allRegions){
                    out.print("'"+localite.getNom()+"',");
                }
            %>
        ],
        datasets: [
            {
                label: 'Logements par région',
                backgroundColor: '#a82a3d',
                data: [
                    <%
                        for (Localite localite : allRegions){
                            out.print(logementsStats.nbrLogementsPerRegion(localite.getId())+",");
                        }
                    %>
                ]
            }
        ]
    };

    var logementsChartOptions = {
        // Boolean - If we should show the scale at all
        showScale: true,
        // Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: false,
        // String - Colour of the grid lines
        scaleGridLineColor: 'rgba(0,0,0,.05)',
        // Number - Width of the grid lines
        scaleGridLineWidth: 1,
        // Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        // Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        // Boolean - Whether the line is curved between points
        bezierCurve: true,
        // Number - Tension of the bezier curve between points
        bezierCurveTension: 0.3,
        // Boolean - Whether to show a dot for each point
        pointDot: false,
        // Number - Radius of each point dot in pixels
        pointDotRadius: 4,
        // Number - Pixel width of point dot stroke
        pointDotStrokeWidth: 1,
        // Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius: 20,
        // Boolean - Whether to show a stroke for datasets
        datasetStroke: true,
        // Number - Pixel width of dataset stroke
        datasetStrokeWidth: 2,
        // Boolean - Whether to fill the dataset with a color
        datasetFill: true,
        // String - A legend template
        <%--legendTemplate: "<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<datasets.length; i++){%><li><span style=\'background-color:<%=datasets[i].lineColor%>\'></span><%=datasets[i].label%></li><%}%></ul>",--%>
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true
    };
    // Create the line chart
    var logementsChart = new Chart(logementChartCanvas, {
        type: 'line',
        data: logementsChartData,
        options: logementsChartOptions
    });
</script>
<script>
    var pieChartCanvas = $('#pieChart').get(0).getContext('2d');
    var pieChart = new Chart(pieChartCanvas);
    var PieData = [
        {
            value:<%out.print(ventesStats.nbrVentesPerRegion(localites.get(0).getId()));%>,
            color: '#f56954',
            highlight: '#f56954',
            label: ''
        },
        {
            value:<%out.print(ventesStats.nbrVentesPerRegion(localites.get(1).getId()));%>,
            color: '#00a65a',
            highlight: '#00a65a',
            label: '<%out.print(localites.get(1).getNom());%>'
        },
        {
            value:<%out.print(ventesStats.nbrVentesPerRegion(localites.get(2).getId()));%>,
            color: '#f39c12',
            highlight: '#f39c12',
            label: '<%out.print(localites.get(2).getNom());%>'
        },
        {
            value:  <%out.print(ventesStats.nbrVentesPerRegion(localites.get(3).getId()));%>,
            color: '#00c0ef',
            highlight: '#00c0ef',
            label: '<%out.print(localites.get(3).getNom());%>'
        },
        {
            value:  <%out.print(ventesStats.nbrVentesPerRegion(localites.get(4).getId()));%>,
            color: '#3c8dbc',
            highlight: '#3c8dbc',
            label: '<%out.print(localites.get(4).getNom());%>'
        }
    ];
    var pieOptions = {
        // Boolean - Whether we should show a stroke on each segment
        segmentShowStroke: true,
        // String - The colour of each segment stroke
        segmentStrokeColor: '#fff',
        // Number - The width of each segment stroke
        segmentStrokeWidth: 1,
        // Number - The percentage of the chart that we cut out of the middle
        percentageInnerCutout: 50, // This is 0 for Pie charts
        // Number - Amount of animation steps
        animationSteps: 100,
        // String - Animation easing effect
        animationEasing: 'easeOutBounce',
        // Boolean - Whether we animate the rotation of the Doughnut
        animateRotate: true,
        // Boolean - Whether we animate scaling the Doughnut from the centre
        animateScale: false,
        // Boolean - whether to make the chart responsive to window resizing
        responsive: true,
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: false,
        // String - A legend template
        <%--legendTemplate: '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<segments.length; i++){%><li><span style=\'background-color:<%=segments[i].fillColor%>\'></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>',--%>
        // String - A tooltip template
        <%--tooltipTemplate: '<%=value %> <%=label%> users'--%>
    };
    // Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    pieChart.Doughnut(PieData, pieOptions);
</script>

<script type="text/javascript">
    function getLogementId(idTaaLogement) {
        document.getElementById("logementChoisi").value = idTaaLogement;
    }

</script>

<
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
</body>

