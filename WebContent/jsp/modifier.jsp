<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.enums.UserType" %>
<%@ page import="model.beans.views.TablesView" %>
<%@ page import="control.servlets.MyServlet" %>
<%@ page import="model.beans.views.MyView" %>
<%@ page import="model.beans.humans.Employe" %>
<%@ page import="model.beans.Localite" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.db.daos.LocaliteDAO" %>
<!DOCTYPE html>
<html lang="fr">
<%! private TablesView tablesView = new TablesView(); %>
<%
    UserType userType = (UserType) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_TYPE);
    int userId = (int) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER_ID);
    Employe employe = (Employe) request.getSession().getAttribute(MyServlet.LOGGED_IN_USER);

    String currentPage = "CHANGER_MOT_DE_PASSE";
    tablesView.setLoggedInUserId(userId);
    tablesView.setLoggedInUserType(userType);
    tablesView.setCurrentPage(currentPage);
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Modifier</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/fontawesome-all.min.css" rel="stylesheet" type="text/css">
    <link href="../vendor/font-awesome-old/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
    <link href="../css/bootstrapValidator.min.css" rel="stylesheet">

    <style>
        .nav-link:hover {
            background-color: rgba(21, 21, 21, 0.81);
        }

        div.modifierClient, div.modifierEmploye, div.modifierLogement {
            display: none;
        }
    </style>
</head>

<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark navbar-<%out.print(tablesView.getNav().getCssBackgroundClass());%> sidebar fixed-top fixed-top "
     id="mainNav">
    <a class="navbar-brand"
       href="#"><%out.print(tablesView.getNav().getTitle() + ": " + employe.getNom() + " " + employe.getPrenom());%></a>
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

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Principale</li>
    </ol>
    <div class="modifierEmploye">
        <div class="card card-login mx-auto mt-5">
            <div class="card-header">Modifier employé</div>
            <div class="card-body">
                <form method="post" id="modifierEmployeForm" action="/ModifierServlet?modifier=employe">
                    <input type="hidden" name="employeModifie"
                           value="<%out.print(request.getParameter("employeModifie"));%>">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="prenomInput">Prénom</label>
                                <input class="form-control" id="prenomInput" name="prenomInput" type="text">
                            </div>
                            <div class="col-md-6">
                                <label for="nomInput">Nom</label>
                                <input class="form-control" id="nomInput" name="nomInput" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputTel">Téléphone</label>
                                <input class="form-control" id="inputTel" name="inputTel" type="text">
                            </div>
                            <div class="col-md-6">
                                <label for="dateNaissance">Date de naissance</label>
                                <input class="form-control" id="dateNaissance" name="dateNaissance" type="date">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="adresseInput">Adresse</label>
                                <input name="adresseInput" type="text" class="form-control" id="adresseInput">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="exampleInputEmail1">Email address</label>
                                <input class="form-control" id="exampleInputEmail1" name="emailInput" type="email">
                            </div>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>

                    <%--</div>--%>

                    <div class="text-center">
                        <button class="btn btn-primary btn-block" type="submit" value="register">Sauvegarder</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <div class="modifierLogement">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">Ajouter un logement</div>
            <div class="card-body">
                <form method="post" action="/ModifierServlet?modifier=logement" id="modifierLogementForm">
                    <input type="hidden" id="logementModifie"
                           value="<%out.print(request.getParameter("logementModifie"));%>">
                    <div class="form-group">
                        <div class="form-row">
                            <label for="titreInput">Titre</label>
                            <input class="form-control" id="titreInput" name="titreInput" type="text"
                                   aria-describedby="nameHelp" placeholder="Titre">

                        </div>
                        <div class="form-row">
                            <label for="description">Description</label>
                            <input class="form-control" id="description" name="description" type="text"
                                   aria-describedby="nameHelp" placeholder="Description">
                        </div>
                        <div class="form-row">
                            <label for="adresse">Adresse</label>
                            <input class="form-control" id="adresse" name="adresse" type="text"
                                   aria-describedby="nameHelp"
                                   placeholder="Adresse">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-4">
                                <label for="typeLogement">Type</label>
                                <select class="custom-select" id="typeLogement" name="typeLogement">
                                    <option value="villa">Villa</option>
                                    <option value="appartement">Appartement</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="superficie">Superficie</label>
                                <input class="form-control" id="superficie" name="superficie" type="number"
                                       placeholder="Superficie">
                            </div>
                            <div class="col-md-4">
                                <label for="region">Region</label>
                                <select class="custom-select" name="region" id="region">
                                    <%
                                        LinkedList<Localite> list = new LocaliteDAO().getAll();
                                        for (Localite localite : list) {
                                            out.print("<option value=\"" + localite.getId() + "\">" + localite.getNom() + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md">
                                <label for="nbrPcs">Pièces</label>
                                <input class="form-control" id="nbrPcs" name="nbrPcs" type="number"
                                       placeholder="Nombre de pièces">
                            </div>
                            <div class="col-md">
                                <label for="nbrSdb">Salles de bain</label>
                                <input class="form-control" id="nbrSdb" name="nbrSdb" type="number"
                                       placeholder="Nombre de sdb">
                            </div>
                            <div class="col-md">
                                <label for="etage">Etage</label>
                                <input class="form-control" id="etage" name="etage" type="number"
                                       aria-describedby="emailHelp" placeholder="Etage">
                            </div>

                        </div>
                        <div class="form-row">
                            <div class="col-md">
                                <label for="jardin">Avec jardin</label>
                                <input name="jardin" type="checkbox" class="form-control" id="jardin" value="jardin"
                                       placeholder="Avec jardin">
                            </div>
                            <div class="col-md">
                                <label for="garage">Avec garage</label>
                                <input name="garage" type="checkbox" class="form-control" id="garage" value="garage"
                                       placeholder="Avec garage">
                            </div>
                            <div class="col-md">
                                <label for="soussol">Avec sous-sol</label>
                                <input name="soussol" type="checkbox" class="form-control" id="soussol" value="soussol"
                                       placeholder="Avec sous-sol">
                            </div>
                            <div class="col-md">
                                <label for="meubles">Meublé</label>
                                <input name="meubles" id="meubles" type="checkbox" class="form-control"
                                       value="meublespapapa">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md">
                                <label for="prix">Prix</label>
                                <input class="form-control" id="prix" name="prix" type="text" placeholder="Prix">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md">
                                <label for="latitude">Latitude</label>
                                <input class="form-control" id="latitude" name="latitude" type="text"
                                       placeholder="Latitude">
                            </div>
                            <div class="col-md">
                                <label for="longitude">Longitude</label>
                                <input class="form-control" id="longitude" name="longitude" type="text"
                                       placeholder="Longitude">
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <input class="btn btn-primary" type="submit" form="modifierLogementForm"
                               value="Ajouter">
                    </div>


                </form>
            </div>
        </div>
    </div>

    <div class="modifierClient">
        <div class="card card-login mx-auto mt-5">
            <div class="card-header">Modifier client</div>
            <div class="card-body">
                <form method="post" id="modifierClientForm" action="/ModifierServlet?modifier=client">
                    <input type="hidden" name="clientModifie"
                           value="<%out.print(request.getParameter("clientModifie"));%>">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="prenomInput">Prénom</label>
                                <input class="form-control" id="clientprenomInput" name="clientprenomInput" type="text">
                            </div>
                            <div class="col-md-6">
                                <label for="nomInput">Nom</label>
                                <input class="form-control" id="clientnomInput" name="clientnomInput" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputTel">Téléphone</label>
                                <input class="form-control" id="clientinputTel" name="clientinputTel" type="text">
                            </div>
                            <div class="col-md-6">
                                <label for="dateNaissance">Date de naissance</label>
                                <input class="form-control" id="clientdateNaissance" name="clientdateNaissance"
                                       type="date">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="adresseInput">Adresse</label>
                                <input name="clientadresseInput" type="text" class="form-control"
                                       id="clientadresseInput">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="exampleInputEmail1">Email address</label>
                                <input class="form-control" id="clientexampleInputEmail1" name="clientemailInput"
                                       type="email">
                            </div>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>

                    <%--</div>--%>

                    <div class="text-center">
                        <button class="btn btn-primary btn-block" type="submit" value="register">Sauvegarder</button>
                    </div>
                </form>

            </div>
        </div>
    </div>


    <footer class="sticky-footer">
        <div class="container">
            <div class="text-center">
                <small>Copyright © Berrehal-Benghezal-Rehab PFE GL L3 2018</small>
            </div>
        </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="/logout">Logout</a>
                </div>
            </div>
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
<script>

    $(document).ready(function () {
        var page = '<%out.print(request.getParameter("what"));%>';
        switch (page) {
            case "logement":
                $("div.modifierLogement").show();
                break;
            case "client":
                $("div.modifierClient").show();
                break;
            case "employe":
                $("div.modifierEmploye").show();
                break;
        }
        var validator = $("#ajouterLogementForm").bootstrapValidator({
            fields: {
                titreInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer un titre"
                        }
                    }
                },
                description: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer une description"
                        }
                    }
                },
                adresse: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer une adresse"
                        }
                    }
                },
                superficie: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer une adresse"
                        }
                    }
                },
                nbrPcs: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer le nombre de pièces"
                        },
                        integer: {
                            message: "Veuillez entrer un nombre entier"
                        }
                    }
                },
                nbrSdb: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer le nombre de salles de bain"
                        },
                        integer: {
                            message: "Veuillez entrer un nombre entier"
                        }
                    }
                },
                etage: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer l'étage"
                        },
                        integer: {
                            message: "Veuillez entrer un nombre entier"
                        }
                    }
                },
                prix: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer le prix"
                        }
                    }
                },
                latitude: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer la latitude"
                        }
                    }
                },
                longitude: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer la longitutde"
                        }
                    }
                }
            }
        });
        var validator1 = $("#modifierProfilForm").bootstrapValidator({
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
                            message: "Veuillez entrer votre prénom"
                        }
                    }
                },
                inputTel: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre numéro de téléphone"
                        },
                        stringLength: {
                            min: 10,
                            max: 20,
                            message: "Veuillez entrer votre numéro de téléphone"
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
                adresseInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre adresse"
                        }
                    }
                },
                emailInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre adresse email"
                        },
                        emailAddress: {
                            message: "Veuillez entrer une adresse valide (eg. john@gmail.com)"
                        }
                    }
                },
                usernameInput: {
                    validators: {
                        notEmpty: {
                            message: "Veuillez entrer votre nom d'utilisateur"
                        }
                    }
                }
            }
        });
    })
</script>
</html>