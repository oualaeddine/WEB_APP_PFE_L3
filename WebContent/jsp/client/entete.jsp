<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="model.beans.humans.Client" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>

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
<%
    boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    LogementsStats logementsStats = new LogementsStats();

    String active = request.getParameter("what");
    String accueil = "", logements = "", apropos = "", monEspace = "", loggedInClient = "";
    if (active == null) {
        accueil = "active";
    } else {
        if (active.equals("myVisits") || active.equals("mesNotifs") || active.equals("mesVentes") || active.equals("mesLogements")) {
            monEspace = "active";
            System.out.println("mon espace");
        } else if (active.equals("logements")) {
            logements = "active";
            System.out.println("logements");
        }
    }

%>

<div class="header-connect">
    <div class="container">
        <div class="row">
            <div class="col-md-5 col-sm-8  col-xs-12">
                <div class="header-half header-call">
                    <p>
                        <span><i class="pe-7s-call"></i> <%out.print(new ContactInfosDAO().getTelSociete());%></span>
                        <span><i class="pe-7s-mail"></i> eritpimmobilier@gmail.com</span>
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
    if (!isLoggedIn) {
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

                "         <div class=\"button navbar-right\">" +
                "       <button class=\"navbar-btn nav-button wow fadeInRight animated\" onclick=\"window.open('')\" data-wow-delay=\"0.5s\" style=\"visibility: visible; animation-delay: 0.5s; animation-name: fadeInRight;\">Programmer une visite</button>"
                + "             <button class=\"navbar-btn nav-button wow bounceInRight login animated\" onclick=\" window.open('/loginsignup')\" data-wow-delay=\"0.4s\" style=\"visibility: visible; animation-delay:0.4s; animation-name:bounceInRight;\">"
                + "                     Se connecter/S'enregistrer"
                + "             </button>"
                + ""
                + "         </div>"
                + "         <ul class=\"main-nav nav navbar-nav navbar-right\">"
                + "             <li class=\"wow fadeInDown \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"/home\" class=\"" + accueil + "\">Accueil</a>"
                + "             </li>"
                + ""
                + "             <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\"><a class=\"" + logements + "\" href=\"/home?what=logements\">Nos Logements</a>"
                + "             </li>"
                + "             <!-- <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\"><a class=\"\" href=\"property.html\">A propos de <strong>ERITP</strong></a></li> -->"
                + ""
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">A propos de <strong>ERITP</strong> <b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"index-2.html\">Qui somme nous?</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/home?what=stats\">Statistiques</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/home?what=contacter\">Contactez nous</a>"
                + "                     </li>"
                + "                 </ul>"
                + "             </li>"
                + "         </ul>"
                + "     </div>"
                + "     <!-- /.navbar-collapse -->"
                + " </div>"
                + " <!-- /.container-fluid -->" +
                "</nav>" +
                "<!-- End of nav bar -->");
    } else {
        Client client;
        client = (Client) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);


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
                "         <div class=\"button navbar-right\">" +
                "       <button class=\"navbar-btn nav-button wow fadeInRight animated\" onclick=\"window.open('')\" data-wow-delay=\"0.5s\" style=\"visibility: visible; animation-delay: 0.5s; animation-name: fadeInRight;\">Programmer une visite</button></div>"
                + "         <ul class=\"main-nav nav navbar-nav navbar-right\">"
                + "             <li class=\"wow fadeInDown \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"/home\" class=\"" + accueil + "\">Accueil</a>"
                + "             </li>"
                + ""
                + "             <li class=\"wow fadeInDown\" data-wow-delay=\"0.1s\">" +
                "                   <a class=\"" + logements + "\" href=\"/DashboardServlet?what=logements\">Nos Logements</a>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">A propos de <strong>ERITP</strong> <b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"index-2.html\">Qui somme nous?</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=contacter\">Nous contacter</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=stats\">Statistiques</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"index-3.html\">Regles d'utilisation</a>"
                + "                     </li>" +
                "                       <li>" +
                "                           <a href=\"/DashboardServlet?what=sePlaindre\">Se plaindre</a>" +
                "                       </li>"
                + ""
                + "                 </ul>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"" + monEspace + "\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\">Mon espace<b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=myVisits\">Mes visites</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=myWishes\">Ma liste de souhaits</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=mesNotifs\">Mes notifications</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=mesVentes\">Mes ventes en cours</a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a href=\"/DashboardServlet?what=mesLogements\">Mes logements visités</a>"
                + "                     </li>"
                + "                 </ul>"
                + "             </li>"
                + "             <li class=\"dropdown ymm-sw \" data-wow-delay=\"0.1s\">"
                + "                 <a href=\"index.html\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\""
                + "                    data-delay=\"200\"><i class=\"fa fa-user\"></i>     " + client.getUsername() + "<b class=\"caret\"></b></a>"
                + "                 <ul class=\"dropdown-menu navbar-nav\">"
                + "                     <li>"
                + "                         <a class=\"dropdown-toggle\" href=\"/DashboardServlet?what=modifierProfil\" >" +
                "                              Modifier mes informations" +
                "                           </a>"
                + "                     </li>"
                + "                     <li>"
                + "                         <a class=\"dropdown-toggle\" href=\"/DashboardServlet?what=changerMdp\" >" +
                "                              Changer mot de passe" +
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
//        }
    }
%>


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


<script>

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

                <%--$('#counter').text(<%out.print(""+new RapportsStats().positifsNbr());%>);--%>
                <%--$('#counter1').text(<%out.print(""+new LogementsStats().logementsAVendre());%>);--%>
                <%--$('#counter2').text(<%out.print(""+new LocaliteDAO().countAll());%>);--%>
                <%--$('#counter3').text(<%out.print(""+new EmployeDAO().countAll());%>);--%>

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
