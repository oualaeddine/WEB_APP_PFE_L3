<%@ page import="static control.servlets.MyServlet.LOGGED_IN_USER" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: berre
  Date: 4/3/2018
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isLoggedIn = !((request.getSession() == null || request.getSession().getAttribute(LOGGED_IN_USER) == null));
    LogementsStats logementsStats = new LogementsStats();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Modifier mot de passe</title>
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
                <h1 class="page-title">Modification du mot de passe </h1>
            </div>
        </div>
    </div>
</div>


<div class="register-area" style="background-color: rgb(249, 249, 249);">
    <div class="container">

        <div class="col-md-12 text-center">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 register-blocks">
                    <h2>Veuillez entrer votre nouveau mot de passe: </h2>
                    <form action="/Edit" method="post" id="changePasswordForm">
                        <input type="hidden" name="action" value="password">
                        <div class="form-group">
                            <label for="newMdp">Nouveau mot de passe</label>
                            <input class="form-control" name="newMdp" id="newMdp" type="password">
                        </div>
                        <div class="form-group">
                            <label for="confirmMdp">Confirmer votre nouveau mot de passe</label>
                            <input class="form-control" type="password" id="confirmMdp" name="confirmMdp">
                        </div>
                        <div class="form-row">
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
    $(document).ready(function () {
        var validator = $("#changePasswordForm").bootstrapValidator({
            fields: {
                newMdp: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nouveau mot de passe"
                        },
                        stringLength: {
                            min: 6,
                            message: "Le mot de passe doit contenir au moins 6 caracteres"
                        }
                    }
                },
                confirmMdp: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez confirmer le nouveau mot de passe"
                        },
                        identical: {
                            field: "newMdp",
                            message: "Les deux mots de passe ne sont pas identiques"
                        }
                    }
                }
            }
        })
    })
</script>

<script>

    $(function () {
        $("#include_html").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });
</script>
</body>

