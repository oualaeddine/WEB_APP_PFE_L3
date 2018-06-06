<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page import="control.servlets.MyServlet" %>
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
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Nous contacter</title>
    <meta name="description" content="company is a real-estate template">
    <meta name="author" content="Kimarotec">
    <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../../assets/css/normalize.css">
    <link rel="stylesheet" href="../../assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets/css/fontello.css">
    <link href="../../assets_client/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../../assets_client/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
    <link href="../../assets_client/css/animate.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../../assets_client/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets_client/css/icheck.min_all.css">
    <link rel="stylesheet" href="../../assets_client/css/price-range.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.carousel.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.theme.css">
    <link rel="stylesheet" href="../../assets_client/css/owl.transitions.css">
    <link rel="stylesheet" href="../../assets_client/css/style.css">
    <link rel="stylesheet" href="../../assets_client/css/responsive.css">
    <link rel="stylesheet" href="../../css/bootstrapValidator.min.css">
</head>

<body>

<!-- Body content -->
<div id="include_header"></div>


<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">Contactez nous!</h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->

<!-- property area -->
<div class="content-area recent-property padding-top-40" style="background-color: #FFF;">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="" id="contact1">
                    <div class="row">
                        <div class="col-sm-4">
                            <h3><i class="fa fa-map-marker"></i> Addresse</h3>
                            <p><%out.print(new ContactInfosDAO().getAdresseSociete());%>
                            </p>
                        </div>
                        <!-- /.col-sm-4 -->
                        <div class="col-sm-4">
                            <h3><i class="fa fa-phone"></i> Centre des appels</h3>
                            <p class="text-muted">Les appels à ce numeros sont a 100% gratuits si vous appelez depuis
                                Google hangouts, sinon nous vous conseillons d'utiliser le formulaire de contact ci
                                dessous.</p>
                            <p><strong><%out.print(new ContactInfosDAO().getTelSociete());%></strong></p>
                        </div>
                        <!-- /.col-sm-4 -->
                        <div class="col-sm-4">
                            <h3><i class="fa fa-envelope"></i> Support electronique</h3>
                            <p class="text-muted">Sentez vous libres de nous contacter sur notre boite email ou depuis
                                notre platforme de support en-ligne.</p>
                            <ul>
                                <li><strong><a
                                        href="mailto:"><%out.print(new ContactInfosDAO().getEmailSociete());%></a></strong>
                                </li>
                                <li><strong><a href="#contact-form">Message</a></strong> - Notre formulaire de support
                                    en-ligne.
                                </li>
                            </ul>
                        </div>
                        <!-- /.col-sm-4 -->
                    </div>
                    <!-- /.row -->
                    <hr>
                    <div id="map"></div>
                    <hr>
                    <div id="contact-form"></div>
                    <h2>Envoyez nous un message!</h2>

                    <form action="/Contact" method="post" id="contactUsForm">
                        <div class="row">
                            <%
                                if (request.getSession().getAttribute(MyServlet.LOGGED_IN_USER) == null) {
                                    out.print("<div class=\"col-sm-6\">\n" +
                                            "                                <div class=\"form-group\">\n" +
                                            "                                    <label for=\"firstname\">Nom</label>\n" +
                                            "                                    <input type=\"text\" class=\"form-control\" id=\"firstname\" name=\"firstname\">\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                            <div class=\"col-sm-6\">\n" +
                                            "                                <div class=\"form-group\">\n" +
                                            "                                    <label for=\"lastname\">Prénom</label>\n" +
                                            "                                    <input type=\"text\" class=\"form-control\" id=\"lastname\" name=\"lastname\">\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                            <div class=\"col-sm-6\">\n" +
                                            "                                <div class=\"form-group\">\n" +
                                            "                                    <label for=\"email\">Email</label>\n" +
                                            "                                    <input type=\"text\" class=\"form-control\" id=\"email\" name=\"email\">\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                            <div class=\"col-sm-6\">\n" +
                                            "                                <div class=\"form-group\">\n" +
                                            "                                    <label for=\"tel\">Téléphone</label>\n" +
                                            "                                    <input type=\"text\" class=\"form-control\" id=\"tel\" name=\"tel\">\n" +
                                            "                                </div>\n" +
                                            "                            </div>");
                                }
                            %>

                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="objet">Objet</label>
                                    <input type="text" class="form-control" id="objet" name="objet">
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="message">Message</label>
                                    <textarea id="message" name="message" class="form-control" size="500"></textarea>
                                </div>
                            </div>
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Envoyer
                                </button>
                            </div>
                        </div>
                        <!-- /.row -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<!-- Footer area-->

<div id="include_footer"></div>
<script src="../../assets_client/js/modernizr-2.6.2.min.js"></script>

<script src="../../assets_client/js/jquery-1.10.2.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<script src="../../assets_client/js/bootstrap-select.min.js"></script>
<script src="../../assets_client/js/bootstrap-hover-dropdown.js"></script>

<script src="../../assets_client/js/easypiechart.min.js"></script>
<script src="../../assets_client/js/jquery.easypiechart.min.js"></script>
<script src="../../assets_client/js/owl.carousel.min.js"></script>
<script src="../../assets_client/js/wow.js"></script>
<script src="../../assets_client/js/icheck.min.js"></script>
<script src="../../assets_client/js/price-range.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script src="../../assets_client/js/gmaps.js"></script>
<script src="../../assets_client/js/gmaps.init.js"></script>

<script src="../../assets_client/js/main.js"></script>
<script src="../../js/bootstrapValidator.min.js"></script>

<script>
    $(function () {
        $("#include_header").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#include_footer").load("../../jsp/client/footer.jsp");
    });
</script>

</body>
<script type="text/javascript">
    $(document).ready(function () {
        var validator = $("#contactUsForm").bootstrapValidator({
            fields: {
                firstname: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nom"
                        }
                    }
                },
                lastname: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre prénom"
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre adresse email"
                        },
                        emailAddress: {
                            message: "Veuillez entrer une adresse valide (eg: tahar@gmail.com)"
                        },
                        stringLength: {
                            max: 50,
                            message: "Veuillez entrer une adresse valide"
                        }
                    }
                },
                tel: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre numéro de téléphone"
                        },
                        stringLength: {
                            min: 10,
                            max: 20,
                            message: "Veuillez entrer un numéro de téléphone valide"
                        }
                    }
                },
                objet: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer l'objet de votre message"
                        }
                    }
                },
                message: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre message"
                        },
                        stringLength: {
                            max: 500,
                            message: "Votre message est trop long (plus que 500 caractères"
                        }
                    }
                }
            }
        });
    })
</script>
</html>