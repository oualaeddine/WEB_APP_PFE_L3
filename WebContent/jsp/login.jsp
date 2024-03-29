<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.db.ContactInfosDAO" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> - Authentification</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <!--Bootstrap Validator-->
    <link href="../css/bootstrapValidator.min.css" rel="stylesheet"/>
</head>

<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Authentification</div>
        <div class="card-body">
            <form method="post" id="loginForm" action="/login">
                <div class="form-group">
                    <label for="id">Nom d'utilisateur</label>
                    <input class="form-control" id="id" name="userId" type="text"
                           placeholder="Entrez votre nom d'utilisateur">
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input class="form-control" id="password" name="password" type="password"
                           placeholder="Mot de passe">
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">Maintenir la connexion</label>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" style="background-color:#ED8D00;    border-color: #ED8D00;"
                        type="submit">S'authentifier
                </button>
            </form>
            <div class="text-center">
                <a class="d-block small" href="/ForgotPassword">Mot de pass oublié</a>
                <a class="d-block small mt-3" href="../html/register.html">Créer un compte!</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<!--<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>-->
<script src="../vendor/bootstrap/css/bootstrap.min.css"></script>
<script src="../js/bootstrapValidator.min.js" type="text/javascript"></script>


<script src="../js/bootstrapValidator.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var validator = $("#loginForm").bootstrapValidator({
            fields: {
                userId: {
                    message: "Le nom d'utilisateur est obligatoire",
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer un nom d'utilisateur"
                        }
                    }
                },
                password: {
                    message: "Mot de passe obligatoire",
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre mot de passe"
                        }
                    }
                }
            }
        });
    })
</script>
<%
    if (request.getParameter("error") != null) {
        int errorId = Integer.parseInt(request.getParameter("error"));
        String errorMessage = "Unidentified error";
        switch (errorId) {
            case MyServlet.WRONG_CREDENTIALS_ERROR:
                errorMessage = "Utilisateur ou mot de passe incorrect";
                break;
            case MyServlet.REGISTRATION_SUCCESS:
                errorMessage = "Votre compte a ete cree avec succes. Vous pourrez vous connecter une fois que votre compte sera approuve par un administrateur";
                break;
            case MyServlet.REGISTRATION_ERROR:
                errorMessage = "Erreur lors de la création du compte. Veuillez réessayer";
                break;
            case MyServlet.LOGGED_OUT:
                errorMessage = "Vous avez ete deconnecte";
                break;
        }
        out.print("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
%>
</body>
<style>
    .has-error .help-block {
        color: red;
    }

    small.help-block {
        color: #F44336 !important;
    }
</style>
</html>