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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%out.print(new ContactInfosDAO().getNomSociete());%> | Authentification</title>
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
                <h1 class="page-title">Inscription / Connexion </h1>
            </div>
        </div>
    </div>
</div>
<!-- End page header -->


<!-- register-area -->
<div class="register-area" style="background-color: rgb(249, 249, 249);">
    <div class="container">

        <div class="col-md-6">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 register-blocks">
                    <h2>Inscription : </h2>
                    <form action="" method="post" id="registerForm">
                        <input type="hidden" class="form-control" name="tag" value="signup">
                        <div class="form-group">
                            <label for="nomInput">Nom</label>
                            <input type="text" class="form-control" id="nomInput" name="nomInput">
                        </div>
                        <div class="form-group">
                            <label for="prenomInput">Prenom</label>
                            <input type="text" class="form-control" id="prenomInput" name="prenomInput">
                        </div>
                        <div class="form-group">
                            <label for="dateNaissance">Date de naissance</label>
                            <input type="date" class="form-control" id="dateNaissance" name="dateNaissance">
                        </div>
                        <div class="form-group">
                            <label for="emailInput">Email</label>
                            <input type="text" class="form-control" id="emailInput" name="emailInput">
                        </div>
                        <div class="form-group">
                            <label for="inputTel">Numéro de téléphone</label>
                            <input type="text" class="form-control" id="inputTel" name="inputTel">
                        </div>
                        <div class="form-group">
                            <label for="adresseInput">Adresse</label>
                            <input type="text" class="form-control" id="adresseInput" name="adresseInput">
                        </div>
                        <div class="form-group">
                            <label for="usernameInput">nom d'utilisateur</label>
                            <input type="text" class="form-control" id="usernameInput" name="usernameInput">
                        </div>
                        <div class="form-group">
                            <label for="passwordInput">Mot de passe</label>
                            <input type="password" class="form-control" id="passwordInput" name="passwordInput">
                        </div>
                        <div class="form-group">
                            <label for="passwordInput">Confirmation du mot de passe</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-default">Inscription</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 login-blocks">
                    <h2>Connexion : </h2>
                    <form action="" method="post" id="loginForm">
                        <input type="hidden" class="form-control" name="tag" value="login">

                        <div class="form-group">
                            <label for="username">Nom d'utilisateur</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Mot de passe</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-default"> Connexion</button>
                        </div>
                        <div class="text-center">
                            <a href="/ForgotPassword?client=true">Mot de passe oublié</a>
                        </div>
                    </form>
                    <br>
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
            fields: {
                nomInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nom"
                        }
                    }
                },
                prenomInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre prenom"
                        }
                    }
                },
                dateNaissance: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre date de naissance"
                        }
                    }
                },
                emailInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre adresse email"
                        },
                        emailAddress: {
                            message: "L'adresse email doit être valide (eg: john@gmail.com)"
                        },
                        stringLength: {
                            max: 50,
                            message: "L'adresse email ne doit pas dépasser 50 caractères"
                        }
                    }
                },
                inputTel: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre numero de telephone"
                        },
                        stringLength: {
                            min: 10,
                            max: 14,
                            message: "Veuillez entrer un numéro valide"
                        }
                    }
                },
                adresseInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre adresse"
                        }
                    }
                },
                usernameInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer un nom d'utilisateur"
                        },
                    }
                },
                passwordInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer un mot de passe"
                        },
                        stringLength: {
                            min: 6,
                            max: 500,
                            message: "Le mot de passe doit contenir au moins 6 caracteres"
                        }
                    }
                },
                confirmPassword: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez confirmer votre mot de passe"
                        },
                        identical: {
                            field: "passwordInput",
                            message: "Les deux mot de passe ne sont pas identiques"
                        }
                    }
                }

            }
        });
        var validator2 = $("#loginForm").bootstrapValidator({
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nom d'utilisateur"
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre mot de passe"
                        }
                    }
                }
            }
        })
    })
</script>


<%
    if (request.getParameter("error") != null) {
        int errorId = Integer.parseInt(request.getParameter("error"));
        String errorMessage = "Unidentified error";
        switch (errorId) {
            case MyServlet.LOGIN_NEEDED_ERROR_ID:
                errorMessage = "Veuillez vous connecter pour continuer";
                break;
            case MyServlet.WRONG_CREDENTIALS_ERROR:
                errorMessage = "Utilisateur ou mot de passe incorrect";
                break;
            case MyServlet.REGISTRATION_SUCCESS:
                errorMessage = "Votre compte a été créé avec succès. Veuillez vous connecter";
                break;
            case MyServlet.REGISTRATION_ERROR:
                errorMessage = "Erreur lors de la création du compte. Veuillez réessayer";
                break;
        }
        out.print("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
%>
</body>

</html>