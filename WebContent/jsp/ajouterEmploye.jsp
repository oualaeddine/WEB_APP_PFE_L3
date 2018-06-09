<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.views.MyView" %>
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    String currentPage = request.getParameter("page");
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
%>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Ajouter un employé</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <link href="../css/bootstrapValidator.min.css" rel="stylesheet"/>
</head>

<body class="bg-white">
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand" href="#"><%out.print(tablesView.getNav().getTitle()+": "+employe.getNom()+" "+employe.getPrenom());%></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav scroll-nav  navbar-sidenav" id="exampleAccordion">
            <% for (MyView navElement : tablesView.getNav().getElements()) {
                out.print(navElement.getHtml());
            }
            %>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>

    </div>
</nav>
<div class="container" style="margin-top: 10%">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Ajouter un employé</div>
        <div class="card-body">
            <form method="post" action="/AjoutServlet?ajouter=employe" id="ajouterEmployeForm">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="prenomInput">Prénom</label>
                            <input class="form-control" id="prenomInput" name="prenomInput" type="text" aria-describedby="nameHelp" placeholder="Enter first name">
                        </div>
                        <div class="col-md-6">
                            <label for="nomInput">Nom</label>
                            <input class="form-control" id="nomInput" name="nomInput" type="text" aria-describedby="nameHelp" placeholder="Enter last name">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputTel">Numéro de téléphone</label>
                            <input class="form-control" id="inputTel" name="inputTel" type="text" placeholder="Téléphone">
                        </div>
                        <div class="col-md-6">
                            <label for="dateNaissance">Date de naissance</label>
                            <input class="form-control" id="dateNaissance" name="dateNaissance" type="date" aria-describedby="dateHelp" placeholder="Enter birth date">
                        </div>
                        <div class="col-md-6">
                            <label for="exampleInputEmail1">Email address</label>
                            <input class="form-control" id="exampleInputEmail1" name="emailInput" type="email" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="col-md-6">
                            <label for="exampleInputEmail1">Poste</label>
                            <select class="custom-select" name="select" id="type">
                                <option value="agent">Agent</option>
                                <option value="operateur">Operateur</option>
                                <option value="responsable_ventes">Responsable ventes</option>
                                <%
                                    if (userType == UserType.SU) {
                                        out.print("<option value=\"admin\">Admin</option>\n");
                                    }
                                %>
                            </select>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="adresseInput">Adresse</label>
                            <input name="adresseInput" type="text" class="form-control" id="adresseInput" placeholder="Adresse">
                        </div>
                        <div class="col-md-6">
                            <label for="usernameInput">Nom d'utilisateur</label>
                            <input class="form-control" id="usernameInput" name="usernameInput" type="text" aria-describedby="nameHelp" placeholder="Entrez un nom d'utilisateur">
                        </div>
                        <div class="col-md-6">
                            <label for="exampleInputPassword1">Password</label>
                            <input class="form-control" id="exampleInputPassword1" name="passwordInput" type="password" placeholder="Password">
                        </div>

                        <div class="col-md-6">
                            <label for="confirmPassword">Confirm password</label>
                            <input class="form-control" id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirm password">
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <button class="btn btn-primary btn-block" type="submit" value="register">Suivant</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../js/bootstrapValidator.min.js"></script>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        var validator = $("#ajouterEmployeForm").bootstrapValidator({
            fields:{
                prenomInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le prénom"
                        }
                    }
                },
                nomInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le nom"
                        }
                    }
                },
                inputTel:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer le numéro de téléphone"
                        },
                        stringLength:{
                            min:10,
                            message:"Veuillez entrer un numéro de téléphone valide"
                        },

                    }
                },
                dateNaissance:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer la date de naissance"
                        }
                    }
                },
                emailInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer l'adresse email"
                        },
                        stringLength:{
                            min:6,
                            max:35,
                            message:"L'adresse email doit contenir 6-35 caractères"
                        },
                        emailAddress:{
                            message:"\nVeuillez entrer une adresse email valide (exemple: johnsmith@gmail.com)"
                        }
                    }
                },
                usernameInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer un nom d'utilisateur"
                        }
                    }
                },
                passwordInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer un mot de passe"
                        },
                        different:{
                            field:"emailInput",
                            message:"Le mot de passe doit être différent de l'email"
                        },
                        stringLength:{
                            min:6,
                            message:"Le mot de passe doit contenir au moins 6 caractères"
                        }
                    }
                },
                confirmPassword:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez confirmer le mot de passe"
                        },
                        identical:{
                            field:"passwordInput",
                            message:"Veuillez verifier que les 2 mots de passes sont identiques"
                        }
                    }
                },
                adresseInput:{
                    validators:{
                        notEmpty:{
                            message:"Veuillez entrer l'adresse"
                        }
                    }
                }
            }
        })
    });
</script>
<style>
    .has-error .help-block {
        color: red;
    }

    small.help-block {
        color: #F44336 !important;
    }
</style>
</html>