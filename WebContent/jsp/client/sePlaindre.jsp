<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 4/3/2018
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Se plaindre</title>
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
    <link rel="stylesheet" href="../../css/bootstrapValidator.min.css">


</head>

<body>
<div id="include_html"></div>

<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">Reporter une plainte </h1>
            </div>
        </div>
    </div>
</div>


<div class="register-area" style="background-color: rgb(249, 249, 249);">
    <div class="container">

        <div class="col-md-12 text-center">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 register-blocks">
                    <h2>Dites nous ce que vous pensez : </h2>
                    <form action="/Contact" method="post" id="sePlaindreForm">
                        <input type="hidden" name="plainte" value="true">
                        <div class="form-group">
                            <label for="content">Entrez le motif de votre plainte ici</label>
                            <textarea class="form-control" name="content" id="content" rows="5"></textarea>
                        </div>
                        <div class="form-group">
                            <button type="submit" class=" btn btn-primary ">Envoyer</button>
                        </div>
                    </form>
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
<script src="../../js/bootstrapValidator.min.js"></script>
<script>

    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });

    $(document).ready(function () {
        var validator = $("#sePlaindreForm").bootstrapValidator({
            fields: {
                content: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer le motif de votre plainte"
                        },
                        stringLength: {
                            max: 200,
                            message: "Votre message est trop long (plus de 200 caractères)"
                        }
                    }
                }
            }
        })
    })
</script>
</body>

