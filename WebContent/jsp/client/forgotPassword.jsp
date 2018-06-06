<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
    <script type="text/javascript">
        function greeting() {
            alert("L'email a été envoyé à " + document.forms["form1"]["emailAdress"].value)
        }
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Mot de passe oublié</title>
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
    <link href="../../css/bootstrapValidator.min.css" rel="stylesheet">
</head>

<body>
<div id="include_html"></div>


<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">Mot de passe oublié </h1>
            </div>
        </div>

        <div class="card card-login mx-auto mt-5">
            <div class="card-header" align="center">Réinitialiser le mot de passe</div>
            <div class="card-body">
                <div class="text-center mt-4 mb-5">
                    <h4>Mot de passe oublié ?</h4>
                    <p>Entrez votre adresse email<br>
                        Nous vous enverrons les instructions relatives à la réinitialisation de votre mot de passe</p>

                </div>
                <form method="post" onsubmit="greeting()" name="form1">
                    <input type="hidden" name="client" value="true">
                    <div class="form-group">
                        <input class="form-control" id="exampleInputEmail1" name="emailAdress" type="email"
                               aria-describedby="emailHelp" placeholder="Enter email address">
                    </div>

                    <button class="btn btn-primary btn-block" type="submit">Réinitialiser mot de passe</button>
                </form>
                <div class="text-center">
                    <a class="d-block small mt-3" href="/loginsignup">S'authentifier ou créer un nouveau compte</a>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- End page header -->


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
        $("#include_html").load("../../jsp/client/entete.jsp");
    });
    $(function () {
        $("#footer_include").load("../../jsp/client/footer.jsp");
    });
</script>

<script src="../../js/bootstrapValidator.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var validator = $("#registerForm").bootstrapValidator({
            fields: {}
        })
    });
</script>

</body>

</html>